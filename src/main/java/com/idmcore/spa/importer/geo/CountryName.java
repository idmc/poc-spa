package com.idmcore.spa.importer.geo;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * Bean used to import import csv file as well as export to elasticsearch.
 */
@CsvDataType
@Document(indexName = "spa_countrynames")
@Mapping(mappingPath = "spa-default-mapping.json")
@Setting(settingPath = "settings.json")
public class CountryName {
    @Id
    private String identifier;

    @CsvField(pos = 1)
    private String country;
    @CsvField(pos = 2)
    private String language;
    @CsvField(pos = 3)
    private String name;

    public CountryName() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
