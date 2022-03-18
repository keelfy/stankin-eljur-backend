package org.keelfy.eljur.data.embeddable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.keelfy.eljur.data.Credentials;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.ZonedDateTime;

/**
 * @author Egor Kuzmin
 */
@Data
@Embeddable
public class ModificationInfo {

    @CreatedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "created_by_id", referencedColumnName = "id")
    private Credentials createdBy;

    @LastModifiedBy
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "last_modified_by_id", referencedColumnName = "id")
    private Credentials lastModifiedBy;

    @CreationTimestamp
    @JsonIgnore
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @JsonIgnore
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
