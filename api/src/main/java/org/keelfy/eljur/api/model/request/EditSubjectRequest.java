package org.keelfy.eljur.api.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
public class EditSubjectRequest {

    @NotNull
    private String name;

}
