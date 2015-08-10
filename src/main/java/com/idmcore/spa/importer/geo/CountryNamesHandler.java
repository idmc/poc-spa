package com.idmcore.spa.importer.geo;

import com.idmcore.spa.elastic.CountryNamesCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Handler to store {@link CountryName} objects.
 */
@Component
public class CountryNamesHandler implements GenericHandler<List<CountryName>> {
    @Autowired
    private CountryNamesCrudRepository countryNamesCrudRepository;

    @Override
    public Object handle(List<CountryName> payload, Map<String, Object> headers) {
        countryNamesCrudRepository.save(payload);
        return null;
    }
}
