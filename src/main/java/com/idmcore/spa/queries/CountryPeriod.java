package com.idmcore.spa.queries;

/**
 * Used to return a bucket from a data histogram in elastic.
 */
public class CountryPeriod {
    private String key;
    private CountryCounts counts;

    public CountryPeriod(String key, CountryCounts counts) {
        this.key = key;
        this.counts = counts;
    }

    public String getKey() {
        return key;
    }

    public CountryCounts getCounts() {
        return counts;
    }
}
