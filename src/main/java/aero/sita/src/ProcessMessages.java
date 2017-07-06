package aero.sita.src;

import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/**
 * Process Messages for the output and create message with new content.
 *

 *
 */
public class ProcessMessages {

    /**
     * buildMessages
     * @param message , not null.
     * @return Object , never null.
     */
    public Object getMessages(Message<?> message) {
        String messageContent = getData(message.getPayload().toString());
        Message<String> output = MessageBuilder.withPayload(messageContent).copyHeaders(message.getHeaders()).build();
        return output;
    }

    /**
     * getPayloadData
     * @param payload , not null.
     * @return the sum of the lines, never null.
     */
    private String getData(String payload) {
        long add = 0;
        String[] lines = payload.split("\n");
        for (String line : lines) {
        	add = add + Long.valueOf(line.trim());
        }
        return String.valueOf(add);
    }
}
