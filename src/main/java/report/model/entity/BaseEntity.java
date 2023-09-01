package report.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false,
            nullable = false, name = "id")
    private UUID id;

    private Long version;

    public BaseEntity() {
    }

    public BaseEntity(UUID id, Long version) {
        this.id = id;
        this.version = version;
    }

    public UUID getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }
}
