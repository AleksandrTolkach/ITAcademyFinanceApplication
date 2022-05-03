package by.tolkach.schedulerAccount.dao.api.entity;

import by.tolkach.schedulerAccount.dto.ScheduleTimeUnit;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(schema = "application", name = "schedules")
public class ScheduleEntity {

    @Id
    @GeneratedValue
    private UUID uuid;
    private LocalDateTime startTime;
    private LocalDateTime stopTime;
    private long interval;
    @Enumerated(EnumType.STRING)
    private ScheduleTimeUnit timeUnit;

    public ScheduleEntity() {
    }

    public ScheduleEntity(UUID uuid, LocalDateTime startTime, LocalDateTime stopTime, long interval,
                          ScheduleTimeUnit timeUnit) {
        this.uuid = uuid;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.interval = interval;
        this.timeUnit = timeUnit;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getStopTime() {
        return stopTime;
    }

    public void setStopTime(LocalDateTime stopTime) {
        this.stopTime = stopTime;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public ScheduleTimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(ScheduleTimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public static class Builder {

        private UUID uuid;
        private LocalDateTime startTime;
        private LocalDateTime stopTime;
        private long interval;
        private ScheduleTimeUnit timeUnit;

        private Builder() {
        }

        public Builder(UUID uuid, LocalDateTime startTime, LocalDateTime stopTime, long interval, ScheduleTimeUnit timeUnit) {
            this.uuid = uuid;
            this.startTime = startTime;
            this.stopTime = stopTime;
            this.interval = interval;
            this.timeUnit = timeUnit;
        }

        public static Builder createBuilder() {
            return new Builder();
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setStopTime(LocalDateTime stopTime) {
            this.stopTime = stopTime;
            return this;
        }

        public Builder setInterval(long interval) {
            this.interval = interval;
            return this;
        }

        public Builder setTimeUnit(ScheduleTimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public ScheduleEntity build() {
            return new ScheduleEntity(uuid, startTime, stopTime, interval, timeUnit);
        }
    }
}
