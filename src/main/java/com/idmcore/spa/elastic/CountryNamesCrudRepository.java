package com.idmcore.spa.elastic;

import com.idmcore.spa.importer.geo.CountryName;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring elasticsearch repository class for the {@link CountryName} class. There is no concrete implementation
 * for this class. This is dynamically handled by spring.
 */
public interface CountryNamesCrudRepository extends ElasticsearchRepository<CountryName, String> {
    /**
     * Returns the {@link CountryName} object for the provided name and in the provided language.
     * @param countryName String containing the country to obtain.
     * @param language Language to obtain the country for
     * @return CountryName object that was found.
     */
    CountryName findOneByNameAndLanguage(String countryName, String language);
}
