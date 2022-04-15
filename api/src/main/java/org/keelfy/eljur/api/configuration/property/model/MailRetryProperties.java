package org.keelfy.eljur.api.configuration.property.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 * */
@Data
@Validated
public class MailRetryProperties {

    /**
     * Интервал между попытками отправки писем.
     * */
    @NotNull
    private Long interval;

    /**
     * Максимальное количество попыток переотправки.
     * */
    @NotNull
    private Integer maxAttempts;

}
