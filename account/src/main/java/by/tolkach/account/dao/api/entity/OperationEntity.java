package by.tolkach.account.dao.api.entity;

import by.tolkach.account.dto.Currency;
import by.tolkach.account.dto.OperationType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "finance", name = "operations")
public class OperationEntity {

    @Id
    @Column(name = "\"id\"")
    private UUID id;
    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    @Column(name = "\"description\"")
    private String description;
    private long value;
    @Column(name = "\"type\"")
    @Enumerated(EnumType.STRING)
    private OperationType type;
    private Currency currency;
    @ManyToOne
    private AccountEntity account;

    public OperationEntity() {
    }

    public OperationEntity(UUID id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String description,
                           long value, OperationType type, Currency currency, AccountEntity account) {
        this.id = id;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.description = description;
        this.value = value;
        this.type = type;
        this.currency = currency;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public static class Builder {

        private UUID id;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private String description;
        private long value;
        private OperationType type;
        private Currency currency;
        private AccountEntity account;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setId(UUID id) {
            this.id = id;
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

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setValue(long value) {
            this.value = value;
            return this;
        }

        public Builder setType(OperationType type) {
            this.type = type;
            return this;
        }

        public Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Builder setAccount(AccountEntity account) {
            this.account = account;
            return this;
        }

        public OperationEntity build() {
            return new OperationEntity(id, dtCreate, dtUpdate, description, value, type, currency, account);
        }
    }
}
