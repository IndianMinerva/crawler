package com.crawler.service;

import com.crawler.pojo.UrlData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class PageAnalyticsServiceTest {

    @Autowired
    private PageAnalyticsService pageAnalyticsService;

    @Test
    public void convertUrlsToLinkDataSorted() {
        List<String> javaScriptLinks = Arrays.asList("jqury.js", "angular.js", "react.js",
                "minify.js", "angular.js", "something_else.js", "react.js", "angular.js",
                "minify.js", "everyting.js", "nothing.js", "angular.js",
                "fish.js", "wordpress.js", "oracle.js", "reach.js", "blogspot.com");

        List<UrlData> urlData = pageAnalyticsService.convertUrlsToLinkDataSorted(javaScriptLinks);

        System.out.println(urlData);
    }
}
