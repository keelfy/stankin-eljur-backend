package org.keelfy.eljur.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.keelfy.eljur.data.model.InvitationStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
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
@ToString(of = "od")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table(name = "invitation", indexes = {
})
public class Invitation {

    @Id
    @SequenceGenerator(name = "invitationIdSeq", sequenceName = "invitation_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "invitationIdSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique = true, length = 36)
    private String invitationId;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    @ManyToOne
    @JoinColumn(name = "invited", referencedColumnName = "id")
    private Credentials invited;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "id")
    private Credentials creator;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime validUntil;

}
