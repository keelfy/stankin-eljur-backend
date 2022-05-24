package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class SubjectResponse {

    private Long id;

    private String name;

}
