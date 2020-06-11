package com.crawler.utils;

import com.crawler.config.AppConfig;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SiteService {
    private final AppConfig appConfig;

    @Autowired
    public SiteService(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public static Document getWebPageAsDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")
                .timeout(5000).get();
    }
}
