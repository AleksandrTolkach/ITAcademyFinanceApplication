package by.tolkach.mailScheduler.dao.api.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "scheduled_mails")
public class ScheduledMailEntity extends EssenceEntity {

    private LocalDateTime dtCreate;
    private LocalDateTime dtUpdate;
    @OneToOne
    private MailEntity mail;
    @OneToOne
    private ParamEntity param;
    @OneToOne
    private ScheduleEntity schedule;

    public ScheduledMailEntity() {
    }

    public ScheduledMailEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, MailEntity mailEntity,
                               ParamEntity paramEntity, ScheduleEntity scheduleEntity) {
        super(uuid);
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mailEntity;
        this.param = paramEntity;
        this.schedule = scheduleEntity;
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

    public MailEntity getMail() {
        return mail;
    }

    public void setMail(MailEntity mailEntity) {
        this.mail = mailEntity;
    }

    public ParamEntity getParam() {
        return param;
    }

    public void setParam(ParamEntity paramEntity) {
        this.param = paramEntity;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity scheduleEntity) {
        this.schedule = scheduleEntity;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private MailEntity mailEntity;
        private ParamEntity paramEntity;
        private ScheduleEntity scheduleEntity;

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

        public Builder setMailEntity(MailEntity mailEntity) {
            this.mailEntity = mailEntity;
            return this;
        }

        public Builder setParamEntity(ParamEntity paramEntity) {
            this.paramEntity = paramEntity;
            return this;
        }

        public Builder setScheduleEntity(ScheduleEntity scheduleEntity) {
            this.scheduleEntity = scheduleEntity;
            return this;
        }

        public ScheduledMailEntity build() {
            return new ScheduledMailEntity(uuid, dtCreate, dtUpdate, mailEntity, paramEntity, scheduleEntity);
        }
    }
}
