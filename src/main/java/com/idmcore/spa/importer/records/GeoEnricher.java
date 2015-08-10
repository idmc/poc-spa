package com.idmcore.spa.importer.records;

import com.idmcore.spa.Location;
import com.idmcore.spa.SpaRecord;
import com.idmcore.spa.elastic.CountryNamesCrudRepository;
import com.idmcore.spa.elastic.ZipCodeCrudrepository;
import com.idmcore.spa.importer.ImportErrorReporter;
import com.idmcore.spa.importer.geo.CountryName;
import com.idmcore.spa.importer.geo.ZipItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;

/**
 * The goal for the geo enricher is look up location coordinates and add them to the provided {@link SpaRecord}. First
 * the lookup using zipcode-city-country combination. If no postal code is available we try the city-country combination.
 * <p>
 * In the end there are a few exceptions that are added manually:
 * Vienna-1090
 * Loerenskog
 * <p>
 * Misses are logged using the <em>import.problems</em> name.
 */
@Component
public class GeoEnricher implements GenericTransformer<SpaRecord, SpaRecord> {
    public static final String DEFAULT_COUNTRY_NAMES_LANGUAGE = "EN";

    @Autowired
    private ImportErrorReporter reporter;

    @Autowired
    private ZipCodeCrudrepository zipCodeCrudrepository;

    @Autowired
    private CountryNamesCrudRepository countryNamesCrudRepository;

    @Override
    public SpaRecord transform(SpaRecord source) {
        if (source.getIsolationCountry() != null) {
            CountryName country = countryNamesCrudRepository.findOneByNameAndLanguage(source.getIsolationCountry(), DEFAULT_COUNTRY_NAMES_LANGUAGE);
            if (country != null) {
                source.setIsolationCountryCode(country.getCountry());
            }
        }

        if (source.getIsolationZip() != null) {
            List<ZipItem> byPostalCode = zipCodeCrudrepository.findByPostalCodeAndCountry(source.getIsolationZip(), source.getIsolationCountryCode());
            if (byPostalCode != null && byPostalCode.size() > 0) {
                ZipItem zipItem = byPostalCode.get(0);
                source.setLocation(new Location(Double.parseDouble(zipItem.getLatitude()), Double.parseDouble(zipItem.getLongitude())));
            } else {
                reporter.logError("POSTAL_CODE ZIP", format("zip-%s city-%s country-%s", source.getIsolationZip(), source.getIsolationCity(), source.getIsolationCountry()));
            }
        } else {
            List<ZipItem> byCity = zipCodeCrudrepository.findByLocalityAndCountry(source.getIsolationCity(), source.getIsolationCountryCode());
            if (byCity.size() > 0) {
                ZipItem zipItem = byCity.get(0);
                source.setLocation(new Location(Double.parseDouble(zipItem.getLatitude()), Double.parseDouble(zipItem.getLongitude())));
            } else {
                reporter.logError("POSTAL_CODE ZIP", format("city-%s country-%s", source.getIsolationCity(), source.getIsolationCountry()));
            }
        }
        if (source.getLocation() == null) {
            if ("Vienna".equals(source.getIsolationCity()) && ("1090".equals(source.getIsolationZip()))) {
                source.setLocation(new Location(48.225233, 16.3557922));
            } else if ("Loerenskog".equals(source.getIsolationCity())) {
                source.setLocation(new Location(59.9238705, 10.9574159));
            }
        }
        return source;
    }
}
