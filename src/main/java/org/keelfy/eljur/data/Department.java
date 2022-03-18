package org.keelfy.eljur.data;

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
@Table(name = "department_tab")
public class Department {

    @Id
    @SequenceGenerator(name = "departmentIdSeq", sequenceName = "department_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "departmentIdSeq", strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Credentials> teachers;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "department")
    private List<Group> groups;

    @Embedded
    private ModificationInfo modificationInfo;

}
