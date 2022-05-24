package org.keelfy.eljur.data.entity;

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
import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(name = "id", nullable = false, precision = 38)
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

    @Column(name = "extended_deadline")
    private Boolean extendedDeadline = true;

    @ManyToOne
    @JoinColumn(name = "semester_subject", referencedColumnName = "id", nullable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    private SemesterSubject semesterSubject;

    @ManyToOne
    @JoinColumn(name = "rated_student", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Credentials ratedStudent;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id")
    private Credentials lastModifiedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updatedAt;

}
