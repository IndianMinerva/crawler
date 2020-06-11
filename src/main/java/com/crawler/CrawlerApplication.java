package com.crawler;

import com.crawler.pojo.UrlData;
import com.crawler.service.GoogleSearchService;
import com.crawler.service.PageAnalyticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;
import java.util.Scanner;


@SpringBootApplication
@EnableConfigurationProperties
@ComponentScan(basePackages = "com.crawler")
public class CrawlerApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleSearchService.class);

    public static void main(String[] args) {
        final ApplicationContext ctx = new AnnotationConfigApplicationContext("com.crawler");

        final Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a search string: ");
        final String searchString = scanner.next(); // "emperor penguin";
        List<UrlData> frameworks = ctx.getBean(PageAnalyticsService.class).getJavaScriptLinksSorted(searchString);
        LOGGER.info("Mostly used Javascript framkes are : {}", frameworks);
    }
}
