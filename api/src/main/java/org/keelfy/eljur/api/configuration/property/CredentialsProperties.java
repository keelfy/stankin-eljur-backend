package org.keelfy.eljur.api.configuration.property;

import lombok.Data;
import org.keelfy.eljur.api.configuration.property.model.CredentialsPasswordProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Validated
@ConfigurationProperties(prefix = "app.credentials")
public class CredentialsProperties {

    /**
     * Настройки пароля и его контроля.
     */
    @NotNull
    private CredentialsPasswordProperties password = new CredentialsPasswordProperties();

}
