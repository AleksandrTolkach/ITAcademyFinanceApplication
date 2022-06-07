package by.tolkach.mailScheduler.dto.scheduledMail;

import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeDeserializer;
import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScheduledMail extends Essence {

    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime dtCreate;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime dtUpdate;
    private Mail mail;
    private Param param;
    private Schedule schedule;

    public ScheduledMail() {
    }

    public ScheduledMail(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, Mail mail, Param param, Schedule schedule) {
        super(uuid);
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.param = param;
        this.schedule = schedule;
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

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private Mail mail;
        private Param param;
        private Schedule schedule;

        private Builder() {
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
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

        public Builder setMail(Mail mail) {
            this.mail = mail;
            return this;
        }

        public Builder setParam(Param param) {
            this.param = param;
            return this;
        }

        public Builder setSchedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public ScheduledMail build() {
            return new ScheduledMail(uuid, dtCreate, dtUpdate, mail, param, schedule);
        }
    }
}
