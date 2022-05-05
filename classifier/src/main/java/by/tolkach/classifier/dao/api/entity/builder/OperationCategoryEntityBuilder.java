package by.tolkach.classifier.dao.api.entity.builder;

import by.tolkach.classifier.dao.api.entity.OperationCategoryEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class OperationCategoryEntityBuilder {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;

    private OperationCategoryEntityBuilder() {
    }

    public static OperationCategoryEntityBuilder createBuilder() {
        return new OperationCategoryEntityBuilder();
    }

    public OperationCategoryEntityBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public OperationCategoryEntityBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public OperationCategoryEntityBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public OperationCategoryEntityBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public OperationCategoryEntity build() {
        return new OperationCategoryEntity(uuid, dtCreate, dtUpdate, title);
    }
}
