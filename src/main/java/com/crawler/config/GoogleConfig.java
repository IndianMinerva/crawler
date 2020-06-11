package com.crawler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class GoogleConfig {
    @Value("${google.urlPattern}")
    private String urlPattern;

    @Value("${google.resultCount}")
    private int resultCount;

    @Value("${google.resultPath}")
    private String resultPath;

    public String getUrlPattern() {
        return urlPattern;
    }

    public int getResultCount() {
        return resultCount;
    }

    public String getResultPath() {
        return resultPath;
    }
}
