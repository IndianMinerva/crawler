package com.crawler.service;

import com.crawler.config.AppConfig;
import com.crawler.pojo.UrlData;
import com.crawler.utils.JavaScriptUtils;
import com.crawler.worker.PageParserWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Service
public class PageAnalyticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageAnalyticsService.class);

    private final SearchService searchService;
    private final AppConfig appConfig;

    @Autowired
    public PageAnalyticsService(final GoogleSearchService googleSearchService, final AppConfig appConfig) {
        this.searchService = googleSearchService;
        this.appConfig = appConfig;
    }

    public List<UrlData> getJavaScriptLinksSorted(final String searchString) {
        final List<String> javaScriptUrls = new LinkedList<>();
        final Queue<String> searchResults = searchService.getLinksFromSearch(searchString);

        for (Future<List<String>> future : startWorkersAndGetFutures(searchResults)) {
            try {
                javaScriptUrls.addAll(future.get());
            } catch (Exception e) {
                LOGGER.error("Error retrieving the results");
            }
        }

        return convertUrlsToLinkDataSorted(javaScriptUrls)
                .stream()
                .limit(appConfig.getElementCount())
                .collect(toList());
    }

    private List<Future<List<String>>> startWorkersAndGetFutures(Queue<String> searchResults) {
        final int numThreads = appConfig.getThreadCount() == 0 ? Runtime.getRuntime().availableProcessors() : appConfig.getThreadCount();
        final ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        final List<PageParserWorker> workers = IntStream.range(0, numThreads)
                .mapToObj(i -> new PageParserWorker(searchResults))
                .collect(Collectors.toList());
        try {
            return executorService.invokeAll(workers);
        } catch (InterruptedException ie) {
            LOGGER.error("Error executing the workers", ie);
            return emptyList();
        } finally {
            executorService.shutdown();
        }
    }

    List<UrlData> convertUrlsToLinkDataSorted(List<String> javaScriptUrls) {
        return javaScriptUrls.stream()
                .filter(JavaScriptUtils::isPublicLibrary)
                .map(JavaScriptUtils::getLibraryNameWithoutVersion)
                .collect(groupingBy(Function.identity(), counting()))
                .entrySet()
                .stream().map(entry -> new UrlData(entry.getKey(), entry.getValue().intValue()))
                .sorted(comparingInt(UrlData::getNoOfOccurrences).reversed())
                .collect(toList());
    }
}
