package org.keelfy.eljur.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.keelfy.eljur.data.model.GradeType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.ZonedDateTime;

/**
 * @author Yegor Kuzmin (keelfy)
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
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Subject subject;

    @CreatedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_id", referencedColumnName = "id", updatable = false)
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_modified_by_id", referencedColumnName = "id")
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
