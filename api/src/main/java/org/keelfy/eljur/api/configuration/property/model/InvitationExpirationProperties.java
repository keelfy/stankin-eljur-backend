package org.keelfy.eljur.api.configuration.property.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 * */
@Data
@Validated
public class InvitationExpirationProperties {

    /**
     * Время истечения приглашения.
     * */
    @NotNull
    private Integer expirationMinutes;

}
