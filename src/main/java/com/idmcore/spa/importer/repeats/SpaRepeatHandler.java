package com.idmcore.spa.importer.repeats;

import com.idmcore.spa.SpaRepeat;
import com.idmcore.spa.elastic.SpaRepeatCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.support.GenericHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Stores {@link SpaRepeat} objects in elasticsearch using the wired repository.
 */
@Component
public class SpaRepeatHandler implements GenericHandler<SpaRepeat> {
    @Autowired
    private SpaRepeatCrudRepository repositorySpaRepeat;

    @Override
    public Object handle(SpaRepeat payload, Map<String, Object> headers) {
        repositorySpaRepeat.save(payload);
        return null;
    }
}
