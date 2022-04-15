package org.keelfy.eljur.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import org.keelfy.eljur.data.model.MailJournalStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Yegor Kuzmin (keelfy)
 */
@Data
@Entity
@ToString(of = "id")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table(name = "mail_journal")
public class MailJournal {

    @Id
    @Column(nullable = false, precision = 38)
    @SequenceGenerator(name = "mailJournalIdSeq", sequenceName = "mail_journal_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "mailJournalIdSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 320)
    private String sender;

    @Column(nullable = false, length = 320)
    private String receiver;

    @Column(length = 512)
    private String subject;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String message;

    @Column(length = 512)
    private String attachment;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private MailJournalStatus status;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String error;

}
