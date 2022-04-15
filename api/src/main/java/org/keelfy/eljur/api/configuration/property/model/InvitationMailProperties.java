package org.keelfy.eljur.api.configuration.property.model;

import lombok.Data;
import org.keelfy.eljur.api.configuration.property.MailMessagePropertiesProvider;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Validated
public class InvitationMailProperties implements MailMessagePropertiesProvider {

    /**
     * Тема письма.
     */
    @NotNull
    private String subject;

    /**
     * Ссылка на использование приглашения.
     */
    @NotNull
    private String receiveInvitationUrl;

    /**
     * Название переменной, используемой в шаблоне для {@link #receiveInvitationUrl}.
     */
    @NotNull
    private String receiveInvitationUrlVariableName = "receiveInvitationUrl";

    /**
     * Название файла шаблона.
     */
    @NotNull
    private String messageTemplateName;

}
