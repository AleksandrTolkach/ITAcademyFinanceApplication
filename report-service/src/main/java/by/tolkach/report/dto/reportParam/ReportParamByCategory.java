package by.tolkach.report.dto.reportParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReportParamByCategory extends ExtendedParam {

    public ReportParamByCategory() {
    }

    public ReportParamByCategory(List<UUID> accounts, LocalDateTime from, LocalDateTime to, List<UUID> categories) {
        super(accounts, from, to, categories);
    }

    public static class Builder {

        private List<UUID> accounts = new ArrayList<>();
        private LocalDateTime from;
        private LocalDateTime to;
        private List<UUID> categories = new ArrayList<>();

        private Builder() {
        }

        public static Builder createBuilder() {
            return new  Builder();
        }

        public Builder setAccounts(List<UUID> accounts) {
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

        public Builder setCategories(List<UUID> categories) {
            this.categories = categories;
            return this;
        }

        public ReportParamByCategory build() {
            return new ReportParamByCategory(accounts, from, to, categories);
        }
    }
}
