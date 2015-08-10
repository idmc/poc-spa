package com.idmcore.spa.importer.types;

import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.messaging.MessageChannel;

/**
 * Flow that takes care of importing {@link com.idmcore.spa.SpaType} objects.
 */
@Configuration
public class SpaTypesFlow {

    @Autowired
    private SpaTypeHandler spaTypeHandler;

    @Autowired
    private SpaTypeToRepeatTransformer spaTypeToRepeatTransformer;

    @Autowired
    private TypesSplitter typesSplitter;

    @Bean(name = "spa.import.types")
    public MessageChannel queueTypesChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public IntegrationFlow handleSpaTypes() {
        return IntegrationFlows.from("spa.import.types")
                .transform(Transformers.fileToString(Charsets.UTF_8.name()))
                .split(typesSplitter, null)
                .transform(spaTypeToRepeatTransformer)
                .handle(spaTypeHandler)
                .get();
    }
}
