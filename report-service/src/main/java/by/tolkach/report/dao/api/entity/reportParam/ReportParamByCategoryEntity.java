package by.tolkach.report.dao.api.entity.reportParam;

import by.tolkach.report.dao.api.entity.AccountsEntity;
import by.tolkach.report.dao.api.entity.CategoriesEntity;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class ReportParamByCategoryEntity extends ExtendedParamEntity {

    public ReportParamByCategoryEntity() {
    }

    public ReportParamByCategoryEntity(AccountsEntity accounts, LocalDateTime from,
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

        public void setAccounts(AccountsEntity accounts) {
            this.accounts = accounts;
        }

        public void setFrom(LocalDateTime from) {
            this.from = from;
        }

        public void setTo(LocalDateTime to) {
            this.to = to;
        }

        public void setCategories(CategoriesEntity categories) {
            this.categories = categories;
        }

        public ReportParamByCategoryEntity build() {
            return new ReportParamByCategoryEntity(accounts, from, to, categories);
        }
    }
}
