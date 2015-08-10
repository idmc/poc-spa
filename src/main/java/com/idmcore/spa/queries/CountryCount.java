package com.idmcore.spa.queries;

/**
 * Used to return information over a country and the number of occurrences in the data store
 */
public class CountryCount {
    private String name;
    private String code;
    private long count;

    public CountryCount(String code, String name, long count) {
        this.code = code;
        this.name = name;
        this.count = count;
    }

    public String getCode() {
        return code;
    }

    public long getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
