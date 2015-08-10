package com.idmcore.spa.importer.types;

import com.idmcore.spa.SpaType;
import com.idmcore.spa.elastic.SpaTypeCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Handler responsible for storing {@link SpaType} objects using the wired repository.
 */
@Component
public class SpaTypeHandler implements GenericHandler<SpaType> {
    @Autowired
    private SpaTypeCrudRepository repositorySpaTypes;

    @Override
    public Object handle(SpaType payload, Map<String, Object> headers) {
        repositorySpaTypes.save(payload);
        return null;
    }
}
