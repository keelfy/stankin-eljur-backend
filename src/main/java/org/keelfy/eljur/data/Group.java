package org.keelfy.eljur.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.keelfy.eljur.data.embeddable.ModificationInfo;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "group_tab")
public class Group {

    @Id
    @SequenceGenerator(name = "groupIdSeq", sequenceName = "group_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "groupIdSeq", strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Credentials> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<GroupSemester> semesters;

    @Embedded
    private ModificationInfo modificationInfo;

}
