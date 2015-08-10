package com.idmcore.spa.elastic;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com/idmcore/spa/elastic")
public class ElasticConfig {

}
