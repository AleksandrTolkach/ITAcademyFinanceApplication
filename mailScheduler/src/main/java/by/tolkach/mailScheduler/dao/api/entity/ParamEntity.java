package by.tolkach.mailScheduler.dao.api.entity;

import by.tolkach.mailScheduler.dao.api.entity.converter.attributesConverter.AccountsAttributeConverter;
import by.tolkach.mailScheduler.dao.api.entity.converter.attributesConverter.CategoriesAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "parameters")
public class ParamEntity extends EssenceEntity {

    @Convert(converter = AccountsAttributeConverter.class)
    private AccountsEntity accounts;
    @Column(name = "\"from\"")
    private LocalDateTime from;
    @Column(name = "\"to\"")
    private LocalDateTime to;
    @Convert(converter = CategoriesAttributeConverter.class)
    private CategoriesEntity categories;

    public ParamEntity() {
    }

    public ParamEntity(UUID uuid, AccountsEntity accounts, LocalDateTime from,
                       LocalDateTime to, CategoriesEntity categories) {
        super(uuid);
        this.accounts = accounts;
        this.from = from;
        this.to = to;
        this.categories = categories;
    }

    public AccountsEntity getAccounts() {
        return accounts;
    }

    public void setAccounts(AccountsEntity accounts) {
        this.accounts = accounts;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public CategoriesEntity getCategories() {
        return categories;
    }

    public void setCategories(CategoriesEntity categories) {
        this.categories = categories;
    }

    public static class Builder {

        private UUID uuid;
        private AccountsEntity accounts;
        private LocalDateTime from;
        private LocalDateTime to;
        private CategoriesEntity categories;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setAccounts(AccountsEntity accounts) {
            this.accounts = accounts;
            return this;
        }

        public Builder setFrom(LocalDateTime from) {
            this.from = from;
            return this;
        }

        public Builder setTo(LocalDateTime to) {
            this.to = to;
            return this;
        }

        public Builder setCategories(CategoriesEntity categories) {
            this.categories = categories;
            return this;
        }

        public ParamEntity build() {
            return new ParamEntity(uuid, accounts, from, to, categories);
        }
    }
}
