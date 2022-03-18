package org.keelfy.eljur.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.embeddable.ModificationInfo;
import org.keelfy.eljur.data.embeddable.SubjectPart;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * @author Egor Kuzmin
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
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id", nullable = false)
    private Group group;

    @Column(name = "ordinal")
    private Integer ordinal;

    @ElementCollection
    @CollectionTable(name = "group_semester_subject_tab", joinColumns = @JoinColumn(name = "group_semester_id", referencedColumnName = "id", nullable = false))
    @MapKeyJoinColumn(name = "subject_id", referencedColumnName = "id")
    private Map<Subject, SubjectPart> subjects;

    @Column(name = "first_module_deadline")
    private ZonedDateTime firstModuleDeadline;

    @Column(name = "second_module_deadline")
    private ZonedDateTime secondModuleDeadline;

    @Column(name = "session_deadline")
    private ZonedDateTime sessionDeadline;

    @Embedded
    private ModificationInfo modificationInfo;

}
