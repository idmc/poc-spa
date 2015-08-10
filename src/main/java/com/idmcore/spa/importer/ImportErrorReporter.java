package com.idmcore.spa.importer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Very basic import error reporter. Uses a logger to send the errors to the logging system.
 */
@Component
public class ImportErrorReporter {
    private static final Logger logger = LoggerFactory.getLogger(ImportErrorReporter.class);

    /**
     * Logs the message using the key and message to the default logging system.
     * @param key String containing the import type
     * @param message String containing the descriptive error
     */
    public void logError(String key, String message) {
        logger.info("{} : {}", key, message);
    }
}
