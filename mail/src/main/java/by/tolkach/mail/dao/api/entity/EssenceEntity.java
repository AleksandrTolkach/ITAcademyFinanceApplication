package by.tolkach.mail.dao.api.entity;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
public abstract class EssenceEntity {

    @Id
    private UUID uuid;

    protected EssenceEntity() {
    }

    protected EssenceEntity(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
