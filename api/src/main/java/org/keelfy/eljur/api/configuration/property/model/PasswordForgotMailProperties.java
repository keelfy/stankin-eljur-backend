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
public class PasswordForgotMailProperties implements MailMessagePropertiesProvider {

    /**
     * Ссылка на страницу восстановления пароля, где %s - токен.
     */
    @NotNull
    private String resetUrl;

    /**
     * Название переменной, представляющий в шаблоне значение {@link #resetUrl}.
     */
    @NotNull
    private String resetUrlVariableName = "resetPasswordUrl";

    /**
     * Тема письма.
     */
    @NotNull
    private String subject;

    /**
     * Название файла с шаблоном.
     */
    @NotNull
    private String messageTemplateName;

}
