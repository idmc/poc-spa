package com.idmcore.spa.importer.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageChannel;

/**
 * Configuration class for the Spring Integration Flow to import zip codes.
 */
@Configuration
public class ZipImportFlow {

    public static final int AGGREGATE_TIMEOUT = 1000;
    public static final int AGGREGATE_SIZE = 100;

    @Autowired
    private ZipItemHandler zipItemHandler;

    @Autowired
    private CountryNamesHandler countryNamesHandler;

    @Bean(name = "spa.import.zipcodes")
    public MessageChannel queueRecordsChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow handleZipCodes() {
        return IntegrationFlows.from("spa.import.zipcodes")
                .aggregate(a ->
                        a.groupTimeout(AGGREGATE_TIMEOUT)
                                .correlationStrategy(message -> "always")
                                .sendPartialResultOnExpiry(true)
                                .expireGroupsUponCompletion(true)
                                .releaseStrategy(g -> g.size() > AGGREGATE_SIZE), null)
                .handle(zipItemHandler)
                .get();
    }

    @Bean(name = "spa.import.countrynames")
    public MessageChannel queueCountryNamesChannel() {
        return MessageChannels.direct().get();
    }

    @Bean
    public IntegrationFlow handleCountryNames() {
        return IntegrationFlows.from("spa.import.countrynames")
                .aggregate(a ->
                        a.groupTimeout(AGGREGATE_TIMEOUT)
                                .correlationStrategy(message -> "always")
                                .sendPartialResultOnExpiry(true)
                                .expireGroupsUponCompletion(true)
                                .releaseStrategy(g -> g.size() > AGGREGATE_SIZE), null)
                .handle(countryNamesHandler)
                .get();
    }

}
