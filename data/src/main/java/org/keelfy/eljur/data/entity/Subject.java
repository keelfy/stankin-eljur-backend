package org.keelfy.eljur.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Table(name = "subject_tab")
public class Subject {

    @Id
    @Column(name = "id", nullable = false, precision = 38)
    @SequenceGenerator(name = "subjectIdSeq", sequenceName = "subject_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "subjectIdSeq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", length = 1000)
    private String name;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "id", updatable = false)
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "last_modified_by", referencedColumnName = "id")
    private Credentials lastModifiedBy;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updatedAt;

}
