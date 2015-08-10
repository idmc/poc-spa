package com.idmcore.spa.queries;

/**
 * Used for the autocomplete to return available types as well as the amount of times they occur.
 */
public class SpaTypeCount {
    private String spaType;
    private long count;

    public SpaTypeCount(String spaType, long count) {
        this.spaType = spaType;
        this.count = count;
    }

    public String getSpaType() {
        return spaType;
    }

    public long getCount() {
        return count;
    }
}
