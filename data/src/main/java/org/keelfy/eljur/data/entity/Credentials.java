package org.keelfy.eljur.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.UpdateTimestamp;
import org.keelfy.eljur.data.model.UserCredentials;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
@Table(name = "credentials_tab", indexes = {
        @Index(name = "i_credentials_username", columnList = "username", unique = true)
})
public class Credentials implements UserCredentials {

    @Id
    @SequenceGenerator(name = "credentialsIdSeq", sequenceName = "credentials_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "credentialsIdSeq", strategy = GenerationType.SEQUENCE)
    private BigInteger id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_credentials_department_id"))
    @NotFound(action = NotFoundAction.IGNORE)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_credentials_group_id"))
    @NotFound(action = NotFoundAction.IGNORE)
    private Group group;

    @Column(name = "roles")
    private String roles;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "enabled")
    private Boolean enabled;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "student")
    private List<StudentSemester> studentSemesters;

    @CreatedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_id", referencedColumnName = "id", updatable = false,
            foreignKey = @ForeignKey(name = "fk_credentials_created_by_id"))
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_modified_by_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_credentials_last_modified_by_id"))
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