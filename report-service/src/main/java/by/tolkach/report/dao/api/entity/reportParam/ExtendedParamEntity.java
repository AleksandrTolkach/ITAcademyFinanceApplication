package by.tolkach.report.dao.api.entity.reportParam;

import by.tolkach.report.dao.api.entity.AccountsEntity;
import by.tolkach.report.dao.api.entity.CategoriesEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class ExtendedParamEntity extends ReportParamByBalanceEntity {

    private LocalDateTime from;
    private LocalDateTime to;
    private CategoriesEntity categories;

    public ExtendedParamEntity() {
    }

    public ExtendedParamEntity(AccountsEntity accounts, LocalDateTime from, LocalDateTime to,
                               CategoriesEntity categories) {
        super(accounts);
        this.from = from;
        this.to = to;
        this.categories = categories;
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

        private AccountsEntity accounts;
        private LocalDateTime from;
        private LocalDateTime to;
        private CategoriesEntity categories;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
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

        public ExtendedParamEntity build() {
            return new ExtendedParamEntity(accounts, from, to, categories);
        }
    }
}
