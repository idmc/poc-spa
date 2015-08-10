package com.idmcore.spa.importer;

import com.idmcore.spa.importer.geo.CountryName;
import com.idmcore.spa.importer.geo.ZipItem;
import net.sf.jsefa.Deserializer;
import net.sf.jsefa.common.lowlevel.filter.HeaderAndFooterFilter;
import net.sf.jsefa.csv.CsvIOFactory;
import net.sf.jsefa.csv.config.CsvConfiguration;
import net.sf.jsefa.csv.lowlevel.config.QuoteMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Service methods related to importing of all spa data
 */
@Service
public class SpaImporter {
    private static final Logger logger = LoggerFactory.getLogger(SpaImporter.class);

    @Autowired
    FileImportGateway readFileGateway;

    @Value("${data.path.types}")
    private String typesPath;

    @Value("${data.path.repeats}")
    private String repeatsPath;

    @Value("${data.path.records}")
    private String spaRecordsPath;

    @Value("${data.path.zipcodes}")
    private String pathZipCodes;

    @Value("${data.path.countrynames")
    private String pathCountryNames;
    /**
     * Import all spa types from the configured path.
     */
    public void importTypes() {
        readFileGateway.readTypes(new File(typesPath));
    }

    public void importRepeats() {
        readFileGateway.readRepeats(new File(repeatsPath));
    }

    public void importSpaRecords() {
        Deserializer deserializer = CsvIOFactory.createFactory(SpaImport.class).createDeserializer();

        Resource resource = new FileSystemResource(spaRecordsPath);
        try (InputStream resourceInputStream = resource.getInputStream()) {
            InputStreamReader reader = new InputStreamReader(resourceInputStream);
            deserializer.open(reader);
            while (deserializer.hasNext()) {
                try {
                    SpaImport imported = deserializer.next();
                    readFileGateway.readRecord(imported);
                } catch (Exception e) {
                    logger.info("Problem with one Spa record", e);
                }
            }
            deserializer.close(true);
        } catch (IOException e) {
            logger.error("Problem with the streams while importing spa records", e);
        }
    }

    public void importZipCodes() {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(',');
        config.setDefaultQuoteMode(QuoteMode.ALWAYS);
        config.setLineFilter(new HeaderAndFooterFilter(1, false, false));
        Deserializer deserializer = CsvIOFactory.createFactory(config, ZipItem.class).createDeserializer();

        Resource resource = new FileSystemResource(pathZipCodes);
        try(InputStream resourceInputStream = resource.getInputStream()) {
            InputStreamReader reader = new InputStreamReader(resourceInputStream);
            deserializer.open(reader);
            long counter = 1;
            while (deserializer.hasNext()) {
                try {
                    ZipItem imported = deserializer.next();
                    imported.setIdentifier(String.valueOf(counter));
                    readFileGateway.readZipCodes(imported);
                    counter++;
                } catch (Exception e) {
                    logger.info("Problem with one zip code", e);
                }
            }
            deserializer.close(true);
        } catch (IOException e) {
            logger.error("Problem while importing the zip codes",e);
        }
    }

    public void importCountryNames() {
        CsvConfiguration config = new CsvConfiguration();
        config.setFieldDelimiter(';');
        config.setLineFilter(new HeaderAndFooterFilter(1, false, false));
        Deserializer deserializer = CsvIOFactory.createFactory(config, CountryName.class).createDeserializer();

        Resource resource = new FileSystemResource(pathCountryNames);
        try(InputStream resourceInputStream = resource.getInputStream()) {
            InputStreamReader reader = new InputStreamReader(resourceInputStream);
            deserializer.open(reader);
            long counter = 1;
            while (deserializer.hasNext()) {
                try {
                    CountryName imported = deserializer.next();
                    imported.setIdentifier(String.valueOf(counter));
                    readFileGateway.readCountryNames(imported);
                    counter++;
                } catch (Exception e) {
                    logger.info("Problem with one country name", e);
                }
            }
            deserializer.close(true);
        } catch (IOException e) {
            logger.error("Problem while importing the country names");
        }

    }
}
