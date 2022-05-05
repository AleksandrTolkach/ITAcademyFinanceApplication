package by.tolkach.classifier.dto.builder;

import by.tolkach.classifier.dto.OperationCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public class OperationCategoryBuilder {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;

    private OperationCategoryBuilder() {
    }

    public static OperationCategoryBuilder createBuilder() {
        return new OperationCategoryBuilder();
    }

    public OperationCategoryBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public OperationCategoryBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public OperationCategoryBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public OperationCategoryBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public OperationCategory build() {
        return new OperationCategory(uuid, dtCreate, dtUpdate, title);
    }
}
