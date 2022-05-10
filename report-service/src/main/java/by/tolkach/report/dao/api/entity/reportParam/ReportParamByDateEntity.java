package by.tolkach.report.dao.api.entity.reportParam;

import by.tolkach.report.dao.api.entity.AccountsEntity;
import by.tolkach.report.dao.api.entity.CategoriesEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class ReportParamByDateEntity extends ExtendedParamEntity {

    public ReportParamByDateEntity() {
    }

    public ReportParamByDateEntity(AccountsEntity accounts, LocalDateTime from,
                                   LocalDateTime to, CategoriesEntity categories) {
        super(accounts, from, to, categories);
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

        public ReportParamByDateEntity build() {
            return new ReportParamByDateEntity(accounts, from, to, categories);
        }
    }
}
