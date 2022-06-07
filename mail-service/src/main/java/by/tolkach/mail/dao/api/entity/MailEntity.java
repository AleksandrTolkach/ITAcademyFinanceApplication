package by.tolkach.mail.dao.api.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "mails")
public class MailEntity extends EssenceEntity {

    private String address;
    private String subject;
    private String text;
    private LocalDateTime date;

    public MailEntity() {
    }

    public MailEntity(UUID uuid, String address, String subject, String text, LocalDateTime date) {
        super(uuid);
        this.address = address;
        this.subject = subject;
        this.text = text;
        this.date = date;
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
        private LocalDateTime date;

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

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public MailEntity build() {
            return new MailEntity(uuid, address, subject, text, date);
        }
    }
}
