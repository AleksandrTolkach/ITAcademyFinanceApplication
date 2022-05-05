package by.tolkach.schedulerAccount.dao.api.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "scheduledOperations")
public class ScheduledOperationEntity extends EssenceEntity {

    @OneToOne
    private ScheduleEntity schedule;
    @OneToOne
    private OperationEntity operation;

    public ScheduledOperationEntity() {
    }

    public ScheduledOperationEntity(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                                    ScheduleEntity schedule, OperationEntity operation) {
        super(uuid, dtCreate, dtUpdate);
        this.schedule = schedule;
        this.operation = operation;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleEntity schedule) {
        this.schedule = schedule;
    }

    public OperationEntity getOperation() {
        return operation;
    }

    public void setOperation(OperationEntity operation) {
        this.operation = operation;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private ScheduleEntity schedule;
        private OperationEntity operation;

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

        public Builder setSchedule(ScheduleEntity schedule) {
            this.schedule = schedule;
            return this;
        }

        public Builder setOperation(OperationEntity operation) {
            this.operation = operation;
            return this;
        }

        public ScheduledOperationEntity build() {
            return new ScheduledOperationEntity(uuid, dtCreate, dtUpdate, schedule, operation);
        }
    }
}
