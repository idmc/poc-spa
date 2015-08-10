package com.idmcore.spa.queries;

import java.util.Map;

/**
 * Used to be able to look up a country by its key.
 */
public class CountryCounts {
    Map<String, CountryCount> counts;

    public CountryCounts(Map<String, CountryCount> counts) {
        this.counts = counts;
    }

    public Map<String, CountryCount> getCounts() {
        return counts;
    }
}
