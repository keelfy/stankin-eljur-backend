package org.keelfy.eljur.data.embeddable;

import lombok.Data;
import org.keelfy.eljur.model.FinalExaminationType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Egor Kuzmin
 */
@Data
@Embeddable
public class SubjectPart {

    @Enumerated(EnumType.STRING)
    @Column(name = "final_examination_type")
    private FinalExaminationType finalExaminationType = FinalExaminationType.NONE;

    @Column(name = "course_work")
    private Boolean courseWork = false;

    @Column(name = "first_module")
    private Boolean firstModule = true;

    @Column(name = "second_module")
    private Boolean secondModule = true;

}
