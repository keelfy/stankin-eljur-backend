package org.keelfy.eljur.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table(name = "student_semester_tab", indexes = {
        @Index(name = "i_student_semester_student_id", columnList = "student_id")
})
public class StudentSemester {

    @Id
    @SequenceGenerator(name = "studentSemesterIdSeq", sequenceName = "student_semester_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "studentSemesterIdSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_student_semester_student_id"))
    private Credentials student;

    @OneToOne
    @JoinColumn(name = "group_semester_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_student_semester_group_semester_id"))
    private GroupSemester groupSemester;

    @OneToMany(mappedBy = "studentSemester", fetch = FetchType.LAZY)
    private List<Grade> grades;

    @CreatedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_id", referencedColumnName = "id", updatable = false,
            foreignKey = @ForeignKey(name = "fk_student_semester_created_by_id"))
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_modified_by_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_student_semester_last_modified_by_id"))
    private Credentials lastModifiedBy;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
