package com.idmcore.spa.importer.records;

import com.idmcore.spa.SpaRecord;
import com.idmcore.spa.elastic.SpaRecordCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Handler class that stores the SpaRecord objects in elastic using the wired repository.
 */
@Component
public class SpaRecordHandler implements GenericHandler<SpaRecord> {
    @Autowired
    private SpaRecordCrudRepository repositorySpaRecord;

    @Override
    public Object handle(SpaRecord payload, Map<String, Object> headers) {
        repositorySpaRecord.save(payload);
        return null;
    }
}
