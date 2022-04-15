package org.keelfy.eljur.api.configuration.property;

import lombok.Data;
import org.keelfy.eljur.api.configuration.property.model.InvitationExpirationProperties;
import org.keelfy.eljur.api.configuration.property.model.InvitationMailProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Настройки приглашений.
 *
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@ConfigurationProperties("app.invitation")
public class InvitationProperties {

    /**
     * Настройки истечения приглашения.
     */
    private InvitationExpirationProperties expiration = new InvitationExpirationProperties();

    /**
     * Настройки уведомления приглашенного пользователя.
     */
    private InvitationMailProperties mail = new InvitationMailProperties();

}

