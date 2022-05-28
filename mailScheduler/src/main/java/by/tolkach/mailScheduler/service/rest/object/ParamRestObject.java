package by.tolkach.mailScheduler.service.rest.object;

import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeDeserializer;
import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ParamRestObject {

    private UUID uuid;
    private List<UUID> accounts;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime from;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime to;
    private List<UUID> categories;

    public ParamRestObject() {
    }

    public ParamRestObject(UUID uuid, List<UUID> accounts, LocalDateTime from, LocalDateTime to, List<UUID> categories) {
        this.uuid = uuid;
        this.accounts = accounts;
        this.from = from;
        this.to = to;
        this.categories = categories;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public List<UUID> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<UUID> accounts) {
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

    public List<UUID> getCategories() {
        return categories;
    }

    public void setCategories(List<UUID> categories) {
        this.categories = categories;
    }

    public static class Builder {

        private UUID uuid;
        private List<UUID> accounts;
        private LocalDateTime from;
        private LocalDateTime to;
        private List<UUID> categories;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
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

        public ParamRestObject build() {
            return new ParamRestObject(uuid, accounts, from, to, categories);
        }
    }
}
