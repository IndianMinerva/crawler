package com.crawler.service;

import java.util.Queue;

public interface SearchService {
    Queue<String> getLinksFromSearch(String queryString);
}
