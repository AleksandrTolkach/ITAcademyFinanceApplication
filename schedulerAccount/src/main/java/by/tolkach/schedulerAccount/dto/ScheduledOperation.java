package by.tolkach.schedulerAccount.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScheduledOperation extends Essence {

    private Schedule schedule;
    private Operation operation;

    public ScheduledOperation() {
    }

    public ScheduledOperation(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate, Schedule schedule, Operation operation) {
        super(uuid, dtCreate, dtUpdate);
        this.schedule = schedule;
        this.operation = operation;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime dtCreate;
        private LocalDateTime dtUpdate;
        private Schedule schedule;
        private Operation operation;

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

        public Builder setSchedule(Schedule schedule) {
            this.schedule = schedule;
            return this;
        }

        public Builder setOperation(Operation operation) {
            this.operation = operation;
            return this;
        }

        public ScheduledOperation build() {
            return new ScheduledOperation(uuid, dtCreate, dtUpdate, schedule, operation);
        }
    }
}
