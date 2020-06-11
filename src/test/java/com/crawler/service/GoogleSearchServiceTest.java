package com.crawler.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Queue;

import static org.junit.Assert.assertFalse;

@SpringBootTest
@ActiveProfiles("test")
public class GoogleSearchServiceTest {

    @Autowired
    private SearchService searchService;

    @Test
    public void testGetLinksFromSearch() {
        Queue<String> links = searchService.getLinksFromSearch("java");
        assertFalse(links.isEmpty());
    }
}
