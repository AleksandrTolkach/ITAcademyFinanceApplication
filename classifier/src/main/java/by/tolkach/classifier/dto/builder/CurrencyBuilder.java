package by.tolkach.classifier.dto.builder;

import by.tolkach.classifier.dto.Currency;

import java.time.LocalDateTime;
import java.util.UUID;

public class CurrencyBuilder {

    private UUID uuid;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    private String title;

    private CurrencyBuilder() {
    }

    public static CurrencyBuilder createBuilder() {
        return new CurrencyBuilder();
    }

    public CurrencyBuilder setUuid(UUID uuid) {
        this.uuid = uuid;
        return this;
    }

    public CurrencyBuilder setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
        return this;
    }

    public CurrencyBuilder setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
        return this;
    }

    public CurrencyBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Currency build() {
        return new Currency(uuid, dtCreate, dtUpdate, title);
    }
}
