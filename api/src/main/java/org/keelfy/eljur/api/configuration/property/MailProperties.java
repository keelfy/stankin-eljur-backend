package org.keelfy.eljur.api.configuration.property;

import lombok.Data;
import org.keelfy.eljur.api.configuration.property.model.MailRetryProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Validated
@ConfigurationProperties(prefix = "app.mail")
public class MailProperties {

    /**
     * Почта-отправитель писем.
     */
    @NotNull
    private String sender;

    /**
     * Коренная папка с прикрепляемыми файлами.
     */
    @NotNull
    private String attachmentDirectory;

    /**
     * Настройки попыток переотправки.
     */
    private MailRetryProperties retry = new MailRetryProperties();

}
