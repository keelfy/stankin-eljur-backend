package org.keelfy.eljur.api.model.response;

import lombok.Data;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.model.FinalExaminationType;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Accessors(chain = true)
public class SemesterSubjectResponse {

    private Long id;

    private String name;

    private FinalExaminationType examinationType;

    private Boolean courseWork;

    private Boolean firstModule;

    private Boolean secondModule;

}
