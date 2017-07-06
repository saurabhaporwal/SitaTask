package aero.sita.src;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

/**
 * Router used for navigating to appropriate channel
 * @author puneet.nigam
 *
 */
public class Router extends AbstractMessageRouter {
    
    /** LOGGER */
    private static final Logger LOGGER = Logger.getLogger(Router.class);
    /** header file name. */
    private static final String HEADER_FILE_NAME = "file_name";
    /** constant for message received. */
    private static final String MSG_RECEIVED = "%s received. Content: %s";
    /** constant for message sent. */
    private static final String MSG_SENT = "File %s is sending to Channel: %s";
    
    /**  channel for output file */
    private MessageChannel outputChannel;
    /**  channel for processed file */
    private MessageChannel processedChannel;
    /**  channel for problem file  */
    private MessageChannel problemChannel;
    
    public void setOutputChannel(MessageChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    public void setProcessedChannel(MessageChannel processedChannel) {
        this.processedChannel = processedChannel;
    }

    public void setProblemChannel(MessageChannel problemChannel) {
        this.problemChannel = problemChannel;
    }

    @Override
    public Collection<MessageChannel> determineTargetChannels(Message<?> msg) {
        Collection<MessageChannel> targetChannels = new ArrayList<MessageChannel>();
        String fileName = (String) msg.getHeaders().get(HEADER_FILE_NAME);
        String content = (String) msg.getPayload();
        LOGGER.debug(String.format(MSG_RECEIVED, fileName, content));
        if (fileContentValidation(content)) {
            targetChannels.add(outputChannel);
            targetChannels.add(processedChannel);
        }
        else {
            targetChannels.add(problemChannel);
        }
        LOGGER.info(String.format(MSG_SENT, fileName, targetChannels));
        return targetChannels;
    }
    
    /**
     * Method for finding the channel.
     * @param content , not null.
     * @return the channel , never null.
     */
    private boolean fileContentValidation(String content) {
       String[] lines = content.split("\n");
           boolean isValid = true;
        for (String line : lines) {
            try {
                Long.parseLong(line.trim());
            }
            catch (NumberFormatException e) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }


}
