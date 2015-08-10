package com.idmcore.spa.importer.geo;

import com.idmcore.spa.elastic.ZipCodeCrudrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Handler class to save {@link ZipItem} objects.
 */
@Component
public class ZipItemHandler implements GenericHandler<List<ZipItem>> {

    @Autowired
    private ZipCodeCrudrepository zipCodeCrudrepository;

    @Override
    public Object handle(List<ZipItem> payload, Map<String, Object> headers) {
        zipCodeCrudrepository.save(payload);
        return null;
    }
}
