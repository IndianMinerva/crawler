package com.crawler.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${thread.count:0}")
    private int threadCount;

    @Value("${element.count:5}")
    private int elementCount;

    public int getThreadCount() {
        return threadCount;
    }

    public int getElementCount() {
        return elementCount;
    }
}
