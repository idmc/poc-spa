package com.idmcore.spa.importer.types;

import com.idmcore.spa.SpaType;
import com.idmcore.spa.importer.ImportErrorReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

/**
 * Receives all the types in one big file and splits the different items using end of line characters. Each line
 * consists of the type followed by a number of repeats using the following format
 * <pre>
 *     t1676,15-12-16-02-16-02-25-17-24-24-24-17
 * </pre>
 */
@Component
public class TypesSplitter extends AbstractMessageSplitter {

    @Autowired
    private ImportErrorReporter reporter;

    @Override
    protected Object splitMessage(Message<?> message) {
        if (message.getPayload() instanceof String) {
            String[] splitted = ((String) message.getPayload()).split("\r\n");

            List<SpaType> items = new ArrayList<>();

            int counter = 0;
            while (counter < splitted.length) {
                String[] fields = splitted[counter].split(",");
                if (fields.length == 2) {
                    items.add(new SpaType(fields[0], fields[1], Arrays.asList(fields[1].split("-"))));
                } else {
                    reporter.logError("SPA_TYPE",format("Format of provided type is not correct: %s",splitted[counter]));
                }
                counter++;
            }

            return items;

        } else {
            return message.getPayload();
        }
    }
}
