package com.idmcore.spa.importer.geo;

import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 * Bean used to import from csv as well as export to elastic.
 */
@CsvDataType
@Document(indexName = "spa_zipcodes")
@Mapping(mappingPath = "spa-default-mapping.json")
@Setting(settingPath = "settings.json")
public class ZipItem {

    @Id
    private String identifier;

    @CsvField(pos = 1)
    private String country;
    @CsvField(pos = 2)
    private String lang;
    @CsvField(pos = 3)
    private String postalCode;
    @CsvField(pos = 4)
    private String region0Code;
    @CsvField(pos = 5)
    private String region0Name;
    @CsvField(pos = 6)
    private String region1Code;
    @CsvField(pos = 7)
    private String region1Name;
    @CsvField(pos = 8)
    private String region2Code;
    @CsvField(pos = 9)
    private String region2Name;
    @CsvField(pos = 10)
    private String region3Code;
    @CsvField(pos = 11)
    private String region3Name;
    @CsvField(pos = 12)
    private String region4Code;
    @CsvField(pos = 13)
    private String region4Name;
    @CsvField(pos = 14)
    private String localityCode;
    @CsvField(pos = 15)
    private String localityType;
    @CsvField(pos = 16)
    private String locality;
    @CsvField(pos = 17)
    private String subLocalityCode;
    @CsvField(pos = 18)
    private String subLocalityType;
    @CsvField(pos = 19)
    private String subLocality;
    @CsvField(pos = 20)
    private String areaCode;
    @CsvField(pos = 21)
    private String areaType;
    @CsvField(pos = 22)
    private String areaName;
    @CsvField(pos = 23)
    private String latitude;
    @CsvField(pos = 24)
    private String longitude;
    @CsvField(pos = 25)
    private String timeZone;
    @CsvField(pos = 26)
    private String daylightSavings;
    @CsvField(pos = 27)
    private String utc;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaType() {
        return areaType;
    }

    public void setAreaType(String areaType) {
        this.areaType = areaType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDaylightSavings() {
        return daylightSavings;
    }

    public void setDaylightSavings(String daylightSavings) {
        this.daylightSavings = daylightSavings;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getLocalityCode() {
        return localityCode;
    }

    public void setLocalityCode(String localityCode) {
        this.localityCode = localityCode;
    }

    public String getLocalityType() {
        return localityType;
    }

    public void setLocalityType(String localityType) {
        this.localityType = localityType;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion0Code() {
        return region0Code;
    }

    public void setRegion0Code(String region0Code) {
        this.region0Code = region0Code;
    }

    public String getRegion0Name() {
        return region0Name;
    }

    public void setRegion0Name(String region0Name) {
        this.region0Name = region0Name;
    }

    public String getRegion1Code() {
        return region1Code;
    }

    public void setRegion1Code(String region1Code) {
        this.region1Code = region1Code;
    }

    public String getRegion1Name() {
        return region1Name;
    }

    public void setRegion1Name(String region1Name) {
        this.region1Name = region1Name;
    }

    public String getRegion2Code() {
        return region2Code;
    }

    public void setRegion2Code(String region2Code) {
        this.region2Code = region2Code;
    }

    public String getRegion2Name() {
        return region2Name;
    }

    public void setRegion2Name(String region2Name) {
        this.region2Name = region2Name;
    }

    public String getRegion3Code() {
        return region3Code;
    }

    public void setRegion3Code(String region3Code) {
        this.region3Code = region3Code;
    }

    public String getRegion3Name() {
        return region3Name;
    }

    public void setRegion3Name(String region3Name) {
        this.region3Name = region3Name;
    }

    public String getRegion4Code() {
        return region4Code;
    }

    public void setRegion4Code(String region4Code) {
        this.region4Code = region4Code;
    }

    public String getRegion4Name() {
        return region4Name;
    }

    public void setRegion4Name(String region4Name) {
        this.region4Name = region4Name;
    }

    public String getSubLocality() {
        return subLocality;
    }

    public void setSubLocality(String subLocality) {
        this.subLocality = subLocality;
    }

    public String getSubLocalityCode() {
        return subLocalityCode;
    }

    public void setSubLocalityCode(String subLocalityCode) {
        this.subLocalityCode = subLocalityCode;
    }

    public String getSubLocalityType() {
        return subLocalityType;
    }

    public void setSubLocalityType(String subLocalityType) {
        this.subLocalityType = subLocalityType;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getUtc() {
        return utc;
    }

    public void setUtc(String utc) {
        this.utc = utc;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "ZipItem{" +
                "areaCode='" + areaCode + '\'' +
                ", identifier='" + identifier + '\'' +
                ", country='" + country + '\'' +
                ", lang='" + lang + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", region0Code='" + region0Code + '\'' +
                ", region0Name='" + region0Name + '\'' +
                ", region1Code='" + region1Code + '\'' +
                ", region1Name='" + region1Name + '\'' +
                ", region2Code='" + region2Code + '\'' +
                ", region2Name='" + region2Name + '\'' +
                ", region3Code='" + region3Code + '\'' +
                ", region3Name='" + region3Name + '\'' +
                ", region4Code='" + region4Code + '\'' +
                ", region4Name='" + region4Name + '\'' +
                ", localityCode='" + localityCode + '\'' +
                ", localityType='" + localityType + '\'' +
                ", locality='" + locality + '\'' +
                ", subLocalityCode='" + subLocalityCode + '\'' +
                ", subLocalityType='" + subLocalityType + '\'' +
                ", subLocality='" + subLocality + '\'' +
                ", areaType='" + areaType + '\'' +
                ", areaName='" + areaName + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", timeZone='" + timeZone + '\'' +
                ", daylightSavings='" + daylightSavings + '\'' +
                ", utc='" + utc + '\'' +
                '}';
    }
}
