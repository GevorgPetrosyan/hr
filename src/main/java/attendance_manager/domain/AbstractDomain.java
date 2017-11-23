package attendance_manager.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Marta Ginosyan
 * Date: 10/22/17
 */

@MappedSuperclass
public class AbstractDomain implements Serializable{

    // region Instance Fields
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    protected Long id;

    @NaturalId
    @Column(name = "ssn")
    @JsonIgnoreProperties
    private String ssn;

    @Column(name = "created_at")
    @JsonIgnoreProperties
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @JsonIgnoreProperties
    private LocalDateTime updatedAt;

    @Column(name = "removed", columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    @JsonIgnoreProperties
    private boolean removed;
    // endregion

    // region Constructors

    public AbstractDomain() {
    }

    public AbstractDomain(String ssn) {
        this.ssn = ssn;
    }
    // endregion

    // region Transient methods
    @PrePersist
    protected void onPrePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onPreUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    // endregion

    // region Getters and Setters
    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void setRemoved(boolean removed) {
        this.removed = removed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    // endregion


    // region Hashcode/equals/toString overrides
    @Override
    public int hashCode() {
        return Objects.hash(ssn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractDomain)) {
            return false;
        }
        AbstractDomain domain = (AbstractDomain) o;
        return Objects.equals(ssn, domain.ssn);
    }
    // endregion

}
