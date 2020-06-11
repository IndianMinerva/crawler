package com.crawler.service;

import com.crawler.config.GoogleConfig;
import com.crawler.utils.SiteService;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * This service is to get the search results from google.com
 */
@Service
public class GoogleSearchService implements SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchService.class);
    private final GoogleConfig googleConfig;
    private static final String HREF = "href";

    public GoogleSearchService(GoogleConfig googleConfig) {
        this.googleConfig = googleConfig;
    }

    public Queue<String> getLinksFromSearch(String queryString) {
        final String searchUrl = String.format(googleConfig.getUrlPattern(), queryString, googleConfig.getResultCount());
        LOGGER.info("The link being searched for is {}", searchUrl);
        final Document doc;
        try {
            doc = SiteService.getWebPageAsDocument(searchUrl);
        } catch (IOException ioe) {
            LOGGER.error("Failed to fetch the search results from google: {}", searchUrl, ioe);
            return new LinkedList<>();
        }
        return doc.select(googleConfig.getResultPath())
                .stream()
                .map(element -> element.attr(HREF))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
