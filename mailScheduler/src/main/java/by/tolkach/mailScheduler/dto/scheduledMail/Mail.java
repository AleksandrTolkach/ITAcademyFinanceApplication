package by.tolkach.mailScheduler.dto.scheduledMail;

import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeDeserializer;
import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Mail extends Essence {

    private String address;
    private String subject;
    private String text;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime date;

    public Mail() {
    }

    public Mail(UUID uuid, String address, String subject, String text) {
        super(uuid);
        this.address = address;
        this.subject = subject;
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public static class Builder {

        private UUID uuid;
        private String address;
        private String subject;
        private String text;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder setText(String text) {
            this.text = text;
            return this;
        }

        public Mail build() {
            return new Mail(uuid, address, subject, text);
        }
    }
}
