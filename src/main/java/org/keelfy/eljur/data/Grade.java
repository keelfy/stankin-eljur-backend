package org.keelfy.eljur.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.keelfy.eljur.data.embeddable.ModificationInfo;
import org.keelfy.eljur.model.GradeType;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigInteger;

/**
 * @author Egor Kuzmin
 */
@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table(name = "grade_tab")
public class Grade {

    @Id
    @SequenceGenerator(name = "gradeIdSeq", sequenceName = "grade_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "gradeIdSeq", strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @Column(name = "value")
    private Integer value;

    @ManyToOne
    @JoinColumn(name = "rated_by_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Credentials ratedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "grade_type")
    private GradeType gradeType;

    @Column(name = "on_time")
    private Boolean onTime = true;

    @ManyToOne
    @JoinColumn(name = "student_semester_id", referencedColumnName = "id", nullable = false)
    private StudentSemester studentSemester;

    @Embedded
    private ModificationInfo modificationInfo;

}
