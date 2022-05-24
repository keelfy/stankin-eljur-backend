package org.keelfy.eljur.api.model.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class CreateSemesterRequest {

    @NotNull
    @Min(1)
    private Long groupId;

    @NotNull
    @Min(1)
    @Max(8)
    private Integer ordinal;

    private Instant firstModuleDeadline;

    private Instant secondModuleDeadline;

    private Instant sessionDeadline;

}
