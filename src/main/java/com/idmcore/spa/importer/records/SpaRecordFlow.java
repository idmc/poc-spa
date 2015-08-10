package com.idmcore.spa.importer.records;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageChannel;

/**
 * Flow that takes care of importing {@link com.idmcore.spa.SpaRecord} objects.
 */
@Configuration
public class SpaRecordFlow {

    @Autowired
    private RecordImportToStoreTransformer toStoreTransformer;

    @Autowired
    private SpaTypeTransformer spaTypeTransformer;

    @Autowired
    private SpaIsolateEnricher isolateEnricher;

    @Autowired
    private SpaRecordHandler spaRecordHandler;

    @Autowired
    private GeoEnricher geoEnricher;

    @Bean(name = "spa.import.records")
    public MessageChannel queueRecordsChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow handleSpaRecords() {
        return IntegrationFlows.from("spa.import.records")
                .transform(toStoreTransformer)
                .transform(spaTypeTransformer)
                .transform(isolateEnricher)
                .transform(geoEnricher)
                .handle(spaRecordHandler)
                .get();
    }

}
