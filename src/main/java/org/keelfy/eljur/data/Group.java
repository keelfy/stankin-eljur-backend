package org.keelfy.eljur.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigInteger;
import java.time.ZonedDateTime;
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
    @JoinColumn(name = "department_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_group_department_id"))
    @NotFound(action = NotFoundAction.IGNORE)
    private Department department;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<Credentials> students;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private List<GroupSemester> semesters;

    @CreatedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_id", referencedColumnName = "id", updatable = false,
            foreignKey = @ForeignKey(name = "fk_group_created_by_id"))
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_modified_by_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_group_last_modified_by_id"))
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
