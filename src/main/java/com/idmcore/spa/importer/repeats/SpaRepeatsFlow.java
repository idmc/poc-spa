package com.idmcore.spa.importer.repeats;

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
 * Flow to handle importing of {@link com.idmcore.spa.SpaRepeat} objects.
 */
@Configuration
public class SpaRepeatsFlow {

    @Autowired
    private SpaRepeatHandler spaRepeatHandler;

    @Autowired
    private RepeatSplitter repeatSplitter;

    @Bean(name = "spa.import.repeats")
    public MessageChannel queueRepeatsChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public IntegrationFlow handleSpaRepeats() {
        return IntegrationFlows.from("spa.import.repeats")
                .transform(Transformers.fileToString(Charsets.UTF_8.name()))
                .split(repeatSplitter, null)
                .handle(spaRepeatHandler)
                .get();
    }

}
