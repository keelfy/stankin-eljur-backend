package org.keelfy.eljur.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.embeddable.ModificationInfo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigInteger;
import java.util.List;

/**
 * @author Egor Kuzmin
 */
@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table(name = "student_semester_tab")
public class StudentSemester {

    @Id
    @SequenceGenerator(name = "studentSemesterIdSeq", sequenceName = "student_semester_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "studentSemesterIdSeq", strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false)
    private Credentials student;

    @OneToOne
    @JoinColumn(name = "group_semester", referencedColumnName = "id", unique = true, nullable = false)
    private GroupSemester groupSemester;

    @OneToMany(mappedBy = "studentSemester", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @Embedded
    private ModificationInfo modificationInfo;

}
