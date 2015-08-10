package com.idmcore.spa.importer;

import com.idmcore.spa.importer.geo.CountryName;
import com.idmcore.spa.importer.geo.ZipItem;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.io.File;

/**
 * Gateway that provides access to the different spring integration flows.
 */
@MessagingGateway
public interface FileImportGateway {

    /**
     * Reads all SpaType objects from the provided file.
     * @param file File to read the spa types from.
     */
    @Gateway(requestChannel = "spa.import.types")
    void readTypes(File file);

    /**
     * Reads all Repeats from the provided file.
     * @param file File to read the repeats from.
     */
    @Gateway(requestChannel = "spa.import.repeats")
    void readRepeats(File file);

    /**
     * Start enriching and transforming the provided SpaImport object to a SpaRecord.
     * @param importedSpa Object to transform.
     */
    @Gateway(requestChannel = "spa.import.records")
    void readRecord(SpaImport importedSpa);

    /**
     * Prepare the provided ZipItem to be stored in elastic.
     * @param zipItem ZipItems to handle.
     */
    @Gateway(requestChannel = "spa.import.zipcodes")
    void readZipCodes(ZipItem zipItem);

    /**
     * Prepare the provided CountryName to be stored in elastic
     * @param countryName CountryName to handle.
     */
    @Gateway(requestChannel = "spa.import.countrynames")
    void readCountryNames(CountryName countryName);
}
