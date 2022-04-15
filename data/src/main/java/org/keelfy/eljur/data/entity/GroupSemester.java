package org.keelfy.eljur.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.keelfy.eljur.data.entity.embeded.SubjectPart;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table(name = "group_semester_tab")
public class GroupSemester {

    @Id
    @SequenceGenerator(name = "groupSemesterIdSeq", sequenceName = "group_semester_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "groupSemesterIdSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_group_semester_group_id"))
    private Group group;

    @Column(name = "ordinal")
    private Integer ordinal;

    @ElementCollection
    @CollectionTable(name = "group_semester_subject_tab", joinColumns =
    @JoinColumn(
            name = "group_semester_id", referencedColumnName = "id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_group_semester_subject_semester_id")))
    @MapKeyJoinColumn(name = "subject_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_group_semester_subject_subject_id"))
    private Map<Subject, SubjectPart> subjects;

    @Column(name = "first_module_deadline")
    private ZonedDateTime firstModuleDeadline;

    @Column(name = "second_module_deadline")
    private ZonedDateTime secondModuleDeadline;

    @Column(name = "session_deadline")
    private ZonedDateTime sessionDeadline;

    @CreatedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_id", referencedColumnName = "id", updatable = false,
            foreignKey = @ForeignKey(name = "fk_group_semester_created_by_id"))
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_modified_by_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_group_semester_last_modified_by_id"))
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
