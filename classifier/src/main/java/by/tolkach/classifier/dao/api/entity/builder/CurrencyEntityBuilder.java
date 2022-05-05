package by.tolkach.classifier.dao.api.entity.builder;

import by.tolkach.classifier.dao.api.entity.CurrencyEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public class CurrencyEntityBuilder {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;

    private CurrencyEntityBuilder() {
    }

    public static CurrencyEntityBuilder createBuilder() {
        return new CurrencyEntityBuilder();
    }

    public CurrencyEntityBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public CurrencyEntityBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public CurrencyEntityBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public CurrencyEntityBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CurrencyEntity build() {
        return new CurrencyEntity(uuid, dtCreate, dtUpdate, title);
    }
}
