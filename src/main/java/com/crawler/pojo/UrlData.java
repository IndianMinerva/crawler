package com.crawler.pojo;

public class UrlData {
    private final String url;
    private final int noOfOccurrences;

    public UrlData(String url, int noOfOccurrences) {
        this.url = url;
        this.noOfOccurrences = noOfOccurrences;
    }

    public String getUrl() {
        return url;
    }

    public int getNoOfOccurrences() {
        return noOfOccurrences;
    }

    @Override
    public String toString() {
        return this.getUrl() + ":" + this.getNoOfOccurrences();
    }
}
