package org.keelfy.eljur.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.keelfy.eljur.data.model.UserCredentials;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;

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
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Entity
@Accessors(chain = true)
@ToString(of = {"id", "email", "username"})
@EqualsAndHashCode(of = "id")
@Table(name = "credentials_tab", indexes = {
        @Index(name = "i_credentials_username", columnList = "username", unique = true)
})
public class Credentials implements UserCredentials {

    @Id
    @Column(name = "id", nullable = false, precision = 38)
    @SequenceGenerator(name = "credentialsIdSeq", sequenceName = "credentials_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "credentialsIdSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "group", referencedColumnName = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Group group;

    @Column(name = "roles", length = 512)
    private String roles;

    @Column(name = "locked")
    private Boolean locked;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "latest_successful_authentication")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime latestSuccessfulAuthentication;

    @Column(name = "record_book_number")
    private String recordBookNumber;

    @Column(name = "student_ticket_number")
    private String studentTicketNumber;

    @CreatedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id")
    private Credentials lastModifiedBy;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at", updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @JsonIgnore
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
