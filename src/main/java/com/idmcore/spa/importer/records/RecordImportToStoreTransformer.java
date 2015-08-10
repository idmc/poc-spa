package com.idmcore.spa.importer.records;

import com.idmcore.spa.SpaRecord;
import com.idmcore.spa.importer.SpaImport;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

/**
 * Transforms {@link SpaImport} to {@link SpaRecord}
 */
@Component
public class RecordImportToStoreTransformer implements GenericTransformer<SpaImport, SpaRecord> {
    @Override
    public SpaRecord transform(SpaImport source) {
        SpaRecord record = new SpaRecord();
        record.setRecordId(String.valueOf(source.getRecordId()));
        record.setIsolateId(source.getIsolateId());
        record.setIsolateDate(source.getIsolateDate());
        record.setSubmissionDate(source.getSubmissionDate());
        record.setUserName(source.getUserName());
        record.setUserOrganisation(source.getUserOrganisation());
        record.setUserCity(source.getUserCity());
        record.setUserCity(source.getUserCity());
        record.setUserZip(source.getUserZip());
        record.setUserCountry(source.getUserCountry());
        record.setUserGroup(source.getUserGroup());
        record.setUserUID(String.valueOf(source.getUserUID().longValue()));
        record.setReliability(source.getReliability());
        record.setSpaType(source.getSpaType());
        record.setMssaMrsa(source.getMssaMrsa());
        record.setMlstType(source.getMlstType());
        record.setPvlProbe(source.getPvlProbe());
        record.setPfgeType(source.getPfgeType());
        record.setSscmedType(source.getSscmedType());
        record.setOrigin(source.getOrigin());
        record.setAcquisition(source.getAcquisition());
        record.setAssociation(source.getAssociation());
        record.setIsolationCountry(source.getIsolationCountry());
        record.setIsolationState(source.getIsolationState());
        record.setIsolationCity(source.getIsolationCity());
        record.setIsolationZip(source.getIsolationZip());
        record.setLitId(source.getLitId());
        record.setComment(source.getComment());
        record.setSyncComment(source.getSyncComment());
        record.setInternalComment(source.getInternalComment());
        record.setUserClientSoftware(source.getUserClientSoftware());
        record.setUserClientSoftwareVersion(source.getUserClientSoftwareVersion());

        return record;
    }
}
