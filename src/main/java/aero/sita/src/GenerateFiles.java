package aero.sita.src;

import org.springframework.integration.file.FileNameGenerator;
import org.springframework.messaging.Message;


/**
 * Generator class handles responsibility of updating file extension.
 *
 */
public class GenerateFiles implements FileNameGenerator {

    /**
     * fileExtension
     */
    private String fileExtension;
    
    /**
     * Getter for fileExt.
     * @return fileExtension, never null.
     */
    public String getFileExtension() {
        return fileExtension;
    }
    
    /**
     * Setter for fileExt.
     * @param fileExt , not null.
    */
    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }
    
    /**
     * generateFileName.
     * @param message , not null
     * @return the updated file name , never null
     */
    public String generateFileName(Message<?> message) {
        String fileName = message.getHeaders().get("file_name").toString();
        return fileName + "." + fileExtension;
    }
}
