package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class DepartmentResponse {

    private Long id;

    private String name;

    private String shortName;

}
