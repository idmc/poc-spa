package com.idmcore.spa.elastic;

import com.idmcore.spa.SpaType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring data elasticsearch repository, no concrete implementation, is generated by spring.
 */
public interface SpaTypeCrudRepository extends ElasticsearchRepository<SpaType, String> {
}
