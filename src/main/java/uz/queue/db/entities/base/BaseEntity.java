package uz.queue.db.entities.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name = "is_deleted")
    private boolean deleted = false;

    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = Objects.nonNull(this.createdAt) ? this.createdAt : new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = Objects.nonNull(this.updatedAt) ? this.updatedAt : new Date();
    }

}
