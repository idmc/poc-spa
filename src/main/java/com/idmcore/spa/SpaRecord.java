package com.idmcore.spa;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;
import java.util.List;

/**
 * Object used to represent imported spa records in elastic
 */
@Document(indexName = "spa_records")
@Mapping(mappingPath = "spa-record-mapping.json")
@Setting(settingPath = "settings.json")
public class SpaRecord {
    @Id
    private String recordId;
    private String isolateId;
    private Date isolateDate;
    private Date submissionDate;
    private String userName;
    private String userOrganisation;
    private String userCity;
    private String userZip;
    private String userCountry;
    private String userGroup;
    private String userUID;
    private int reliability;
    private String spaType;
    private String mssaMrsa;
    private String mlstType;
    private String pvlProbe;
    private String pfgeType;
    private String sscmedType;
    private String origin;
    private String acquisition;
    private String association;
    private String isolationCountry;
    private String isolationCountryCode;
    private String isolationState;
    private String isolationCity;
    private String isolationZip;
    private String litId;
    private String comment;
    private String syncComment;
    private String internalComment;
    private String userClientSoftware;
    private String userClientSoftwareVersion;
    private String repeatsString;
    private List<String> repeats;
    private List<String> repeatsDna;

    private Location location;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(String acquisition) {
        this.acquisition = acquisition;
    }

    public String getAssociation() {
        return association;
    }

    public void setAssociation(String association) {
        this.association = association;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getInternalComment() {
        return internalComment;
    }

    public void setInternalComment(String internalComment) {
        this.internalComment = internalComment;
    }

    public Date getIsolateDate() {
        return isolateDate;
    }

    public void setIsolateDate(Date isolateDate) {
        this.isolateDate = isolateDate;
    }

    public String getIsolateId() {
        return isolateId;
    }

    public void setIsolateId(String isolateId) {
        this.isolateId = isolateId;
    }

    public String getIsolationCity() {
        return isolationCity;
    }

    public void setIsolationCity(String isolationCity) {
        this.isolationCity = isolationCity;
    }

    public String getIsolationCountry() {
        return isolationCountry;
    }

    public void setIsolationCountry(String isolationCountry) {
        this.isolationCountry = isolationCountry;
    }

    public String getIsolationState() {
        return isolationState;
    }

    public void setIsolationState(String isolationState) {
        this.isolationState = isolationState;
    }

    public String getIsolationZip() {
        return isolationZip;
    }

    public void setIsolationZip(String isolationZip) {
        this.isolationZip = isolationZip;
    }

    public String getLitId() {
        return litId;
    }

    public void setLitId(String litId) {
        this.litId = litId;
    }

    public String getMlstType() {
        return mlstType;
    }

    public void setMlstType(String mlstType) {
        this.mlstType = mlstType;
    }

    public String getMssaMrsa() {
        return mssaMrsa;
    }

    public void setMssaMrsa(String mssaMrsa) {
        this.mssaMrsa = mssaMrsa;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPfgeType() {
        return pfgeType;
    }

    public void setPfgeType(String pfgeType) {
        this.pfgeType = pfgeType;
    }

    public String getPvlProbe() {
        return pvlProbe;
    }

    public void setPvlProbe(String pvlProbe) {
        this.pvlProbe = pvlProbe;
    }

    public int getReliability() {
        return reliability;
    }

    public void setReliability(int reliability) {
        this.reliability = reliability;
    }

    public String getSpaType() {
        return spaType;
    }

    public void setSpaType(String spaType) {
        this.spaType = spaType;
    }

    public String getSscmedType() {
        return sscmedType;
    }

    public void setSscmedType(String sscmedType) {
        this.sscmedType = sscmedType;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getSyncComment() {
        return syncComment;
    }

    public void setSyncComment(String syncComment) {
        this.syncComment = syncComment;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getUserClientSoftware() {
        return userClientSoftware;
    }

    public void setUserClientSoftware(String userClientSoftware) {
        this.userClientSoftware = userClientSoftware;
    }

    public String getUserClientSoftwareVersion() {
        return userClientSoftwareVersion;
    }

    public void setUserClientSoftwareVersion(String userClientSoftwareVersion) {
        this.userClientSoftwareVersion = userClientSoftwareVersion;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrganisation() {
        return userOrganisation;
    }

    public void setUserOrganisation(String userOrganisation) {
        this.userOrganisation = userOrganisation;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    public List<String> getRepeatsDna() {
        return repeatsDna;
    }

    public void setRepeatsDna(List<String> repeatsDna) {
        this.repeatsDna = repeatsDna;
    }

    public List<String> getRepeats() {
        return repeats;
    }

    public void setRepeats(List<String> repeats) {
        this.repeats = repeats;
    }

    public String getRepeatsString() {
        return repeatsString;
    }

    public void setRepeatsString(String repeatsString) {
        this.repeatsString = repeatsString;
    }

    public String getIsolationCountryCode() {
        return isolationCountryCode;
    }

    public void setIsolationCountryCode(String isolationCountryCode) {
        this.isolationCountryCode = isolationCountryCode;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "SpaRecord{" +
                "recordId=" + recordId +
                '}';
    }
}
