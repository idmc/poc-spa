package com.idmcore.spa.importer.repeats;

import com.idmcore.spa.SpaRepeat;
import org.springframework.integration.splitter.AbstractMessageSplitter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This splitter receives a big string with the complete content of the repeats file. Structure of the file is repetitive
 * every three lines.
 * <pre>
 * >r01
 * GAGGAAGACAACAACAAGCCTAGC
 *
 * </pre>
 */
@Component
public class RepeatSplitter extends AbstractMessageSplitter {

    @Override
    protected Object splitMessage(Message<?> message) {
        if (message.getPayload() instanceof String) {
            String strMessage = (String) message.getPayload();
            String[] splitted = strMessage.split("\r\n");

            List<SpaRepeat> items = new ArrayList<>();

            int counter = 0;
            while (counter < splitted.length / 3) {
                items.add(new SpaRepeat(splitted[counter], splitted[counter + 1]));
                counter += 3;
            }

            return items;

        } else {
            return message.getPayload();
        }
    }
}
