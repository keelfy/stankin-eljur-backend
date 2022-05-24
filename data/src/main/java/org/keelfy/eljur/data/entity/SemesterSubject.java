package org.keelfy.eljur.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.keelfy.eljur.data.model.FinalExaminationType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Entity
@Accessors(chain = true)
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Table(name = "semester_subject")
public class SemesterSubject {

    @Id
    @Column(name = "id", nullable = false, precision = 38)
    @SequenceGenerator(name = "semesterSubjectIdSeq", sequenceName = "semester_subject_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "semesterSubjectIdSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "semester", referencedColumnName = "id", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "subject", referencedColumnName = "id", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Credentials teacher;

    @Enumerated(EnumType.STRING)
    @Column(name = "final_examination_type")
    private FinalExaminationType finalExaminationType = FinalExaminationType.NONE;

    @Column(name = "course_work")
    private Boolean courseWork = false;

    @Column(name = "first_module")
    private Boolean firstModule = true;

    @Column(name = "second_module")
    private Boolean secondModule = true;

    @Column(name = "coefficient", precision = 2, scale = 1)
    private BigDecimal coefficient;

}

