package com.idmcore.spa.importer;

import com.idmcore.spa.importer.util.DoubleConverterType;
import net.sf.jsefa.csv.annotation.CsvDataType;
import net.sf.jsefa.csv.annotation.CsvField;

import java.util.Date;

/**
 * Object used to import spa records from the csv file.
 */
@CsvDataType
public class SpaImport {
    @CsvField(pos = 1)
    private long recordId;
    @CsvField(pos = 2)
    private String isolateId;
    @CsvField(pos = 3, format = "dd-M-yyyy")
    private Date isolateDate;
    @CsvField(pos = 4, format = "dd-M-yyyy")
    private Date submissionDate;
    @CsvField(pos = 5)
    private String userName;
    @CsvField(pos = 6)
    private String userOrganisation;
    @CsvField(pos = 7)
    private String userCity;
    @CsvField(pos = 8)
    private String userZip;
    @CsvField(pos = 9)
    private String userCountry;
    @CsvField(pos = 10)
    private String userGroup;
    @CsvField(pos = 11, converterType = DoubleConverterType.class)
    private Double userUID;
    @CsvField(pos = 12)
    private int reliability;
    @CsvField(pos = 13)
    private String spaType;
    @CsvField(pos = 14)
    private String mssaMrsa;
    @CsvField(pos = 15)
    private String mlstType;
    @CsvField(pos = 16)
    private String pvlProbe;
    @CsvField(pos = 17)
    private String pfgeType;
    @CsvField(pos = 18)
    private String sscmedType;
    @CsvField(pos = 19)
    private String origin;
    @CsvField(pos = 20)
    private String acquisition;
    @CsvField(pos = 21)
    private String association;
    @CsvField(pos = 22)
    private String isolationCountry;
    @CsvField(pos = 23)
    private String isolationState;
    @CsvField(pos = 24)
    private String isolationCity;
    @CsvField(pos = 25)
    private String isolationZip;
    @CsvField(pos = 26)
    private String litId;
    @CsvField(pos = 27)
    private String comment;
    @CsvField(pos = 28)
    private String syncComment;
    @CsvField(pos = 29)
    private String internalComment;
    @CsvField(pos = 30)
    private String userClientSoftware;
    @CsvField(pos = 31)
    private String userClientSoftwareVersion;

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

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
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

    public Double getUserUID() {
        return userUID;
    }

    public void setUserUID(Double userUID) {
        this.userUID = userUID;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }
}
