package by.tolkach.report.dto.reportParam;

import by.tolkach.report.dto.serializer.LongLocalDateTimeDeserializer;
import by.tolkach.report.dto.serializer.LongLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExtendedParam extends ReportParamByBalance {

    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime from;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime to;
    private List<UUID> categories = new ArrayList<>();

    public ExtendedParam() {
    }

    public ExtendedParam(List<UUID> accounts, LocalDateTime from, LocalDateTime to, List<UUID> categories) {
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

    public List<UUID> getCategories() {
        return categories;
    }

    public void setCategories(List<UUID> categories) {
        this.categories = categories;
    }

    public void addCategory(UUID categoryId) {
        this.categories.add(categoryId);
    }

    public static class Builder {

        private List<UUID> accounts = new ArrayList<>();
        private LocalDateTime from;
        private LocalDateTime to;
        private List<UUID> categories = new ArrayList<>();

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
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

        public ExtendedParam build() {
            return new ExtendedParam(accounts, from, to, categories);
        }
    }
}
