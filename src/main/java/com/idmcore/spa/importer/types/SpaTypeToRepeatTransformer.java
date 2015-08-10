package com.idmcore.spa.importer.types;

import com.idmcore.spa.SpaRepeat;
import com.idmcore.spa.SpaType;
import com.idmcore.spa.elastic.SpaRepeatCrudRepository;
import com.idmcore.spa.importer.ImportErrorReporter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * Find all repeats belonging to the provided {@link SpaType}.
 */
@Component
public class SpaTypeToRepeatTransformer implements GenericTransformer<SpaType, SpaType>, InitializingBean {

    @Autowired
    private SpaRepeatCrudRepository spaRepeatCrudRepository;

    @Autowired
    private ImportErrorReporter reporter;

    private Map<String, SpaRepeat> cachedSpaRepeats = new HashMap<>();

    @Override
    public SpaType transform(SpaType source) {
        if (source.getRepeats() == null || cachedSpaRepeats.isEmpty()) {
            reporter.logError("SPA_TYPE_REPEAT",format("No repeats found for %s",source.gettNumber()));
            return source;
        }
        List<String> repeats = source.getRepeats().stream()
                .filter(s -> cachedSpaRepeats.get(s) != null)
                .map(s -> cachedSpaRepeats.get(s).getDnaString())
                .collect(Collectors.toList());
        source.setRepeatsDna(repeats);
        return source;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        spaRepeatCrudRepository.findAll().forEach(spaRepeat -> {
            String onlyNumber = spaRepeat.getIdentifier().substring(2);
            cachedSpaRepeats.put(onlyNumber, spaRepeat);
        });
    }
}
