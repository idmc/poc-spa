package com.idmcore.spa.importer.records;

import com.idmcore.spa.SpaRecord;
import com.idmcore.spa.SpaType;
import com.idmcore.spa.elastic.SpaTypeCrudRepository;
import com.idmcore.spa.importer.ImportErrorReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import static java.lang.String.format;

/**
 * Transformer to add spa type translations to repeats and the dna string for repeats to the record.
 */
@Component
public class SpaTypeTransformer implements GenericTransformer<SpaRecord, SpaRecord> {

    @Autowired
    private ImportErrorReporter reporter;

    @Autowired
    private SpaTypeCrudRepository repository;

    @Override
    public SpaRecord transform(SpaRecord source) {
        if (source.getSpaType() == null) {
            return source;
        }
        SpaType one = repository.findOne(source.getSpaType());
        if (one == null) {
            reporter.logError("SPA_TYPE", format("Could not find type to map %s", source.getSpaType()));
            return source;
        }
        source.setRepeats(one.getRepeats());
        source.setRepeatsDna(one.getRepeatsDna());
        source.setRepeatsString(one.getRepeatsString());
        return source;
    }
}
