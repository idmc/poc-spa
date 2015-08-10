package com.idmcore.spa;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.ArrayList;
import java.util.List;

/**
 * Object used to represent spa types in elastic.
 */
@Document(indexName = "spa_types")
@Mapping(mappingPath = "spa-type-mapping.json")
@Setting(settingPath = "settings.json")
public class SpaType {
    @Id
    private String tNumber;
    private String repeatsString;
    private List<String> repeats;
    private List<String> repeatsDna;

    public SpaType() {
    }

    public SpaType(String tNumber, String repeatsString) {
        this(tNumber, repeatsString, new ArrayList<>());
    }

    public SpaType(String tNumber, String repeatsString, List<String> repeats) {
        this.tNumber = tNumber;
        this.repeatsString = repeatsString;
        this.repeats = repeats;
    }

    public String gettNumber() {
        return tNumber;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber;
    }

    public List<String> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<String> repeats) {
        this.repeats = repeats;
    }

    public List<String> getRepeatsDna() {
        return repeatsDna;
    }

    public void setRepeatsDna(List<String> repeatsDna) {
        this.repeatsDna = repeatsDna;
    }

    public String getRepeatsString() {
        return repeatsString;
    }

    public void setRepeatsString(String repeatsString) {
        this.repeatsString = repeatsString;
    }

    @Override
    public String toString() {
        return "SpaType{" +
                "repeats=" + repeats +
                ", tNumber='" + tNumber + '\'' +
                '}';
    }
}
