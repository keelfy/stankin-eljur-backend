package org.keelfy.eljur.data;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.embeddable.ModificationInfo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "subject_tab")
public class Subject {

    @Id
    @SequenceGenerator(name = "subjectIdSeq", sequenceName = "subject_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "subjectIdSeq", strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Credentials teacher;

    @Embedded
    private ModificationInfo modificationInfo;

}
