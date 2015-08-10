package com.idmcore.spa.importer.records;

import com.idmcore.spa.SpaRecord;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

/**
 * Transformer to copy the user zip/city/country to the isolation fields if the isolation field is not
 * available in the source.
 */
@Component
public class SpaIsolateEnricher implements GenericTransformer<SpaRecord, SpaRecord> {

    @Override
    public SpaRecord transform(SpaRecord record) {
        if (record.getIsolationCity() != null) {
            return record;
        }
        record.setIsolationZip(record.getUserZip());
        record.setIsolationCity(record.getUserCity());
        record.setIsolationCountry(record.getUserCountry());
        return record;
    }
}
