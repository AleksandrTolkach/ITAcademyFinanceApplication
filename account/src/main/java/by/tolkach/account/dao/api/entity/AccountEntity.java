package by.tolkach.account.dao.api.entity;

import by.tolkach.account.dto.AccountType;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "accounts")
public class AccountEntity extends EssenceEntity {

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private UUID currency;

    public AccountEntity() {
    }

    public AccountEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                         String title, String description, AccountType type, UUID currency) {
        super(uuid, dtCreate, dtUpdate);
        this.title = title;
        this.description = description;
        this.type = type;
        this.currency = currency;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public UUID getCurrency() {
        return currency;
    }

    public void setCurrency(UUID currency) {
        this.currency = currency;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String title;
        private String description;
        private AccountType type;
        private UUID currency;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setType(AccountType type) {
            this.type = type;
            return this;
        }

        public Builder setCurrency(UUID currency) {
            this.currency = currency;
            return this;
        }

        public Builder setDtCreate(LocalDateTime dtCreate) {
            this.dtCreate = dtCreate;
            return this;
        }

        public Builder setDtUpdate(LocalDateTime dtUpdate) {
            this.dtUpdate = dtUpdate;
            return this;
        }

        public AccountEntity build() {
            return new AccountEntity(uuid, dtCreate, dtUpdate, title, description, type, currency);
        }
    }
}