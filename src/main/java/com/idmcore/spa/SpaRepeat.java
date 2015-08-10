package com.idmcore.spa;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * Object used to represent spa repeats in elastic
 */
@Document(indexName = "spa_repeats")
@Mapping(mappingPath = "spa-repeat-mapping.json")
@Setting(settingPath = "settings.json")
public class SpaRepeat {
    @Id
    private String identifier;
    private String dnaString;

    public SpaRepeat() {
    }

    public SpaRepeat(String identifier, String dnaString) {
        this.identifier = identifier;
        this.dnaString = dnaString;
    }

    public String getDnaString() {
        return dnaString;
    }

    public void setDnaString(String dnaString) {
        this.dnaString = dnaString;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
