package org.keelfy.eljur.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.keelfy.eljur.api.configuration.property.MailMessagePropertiesProvider;
import org.keelfy.eljur.api.configuration.property.MailProperties;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {

    private String from;

    private String to;

    private String subject;

    private String message;

    private String attachmentDirectory;

    public MailDto(MailProperties mailProperties, String receiver,
                   MailMessagePropertiesProvider provider, String msg) {

        this.from = mailProperties.getSender();
        this.to = receiver;
        this.subject = provider.getSubject();
        this.message = msg;
        this.attachmentDirectory = provider.getAttachmentDirectory();
    }

}

