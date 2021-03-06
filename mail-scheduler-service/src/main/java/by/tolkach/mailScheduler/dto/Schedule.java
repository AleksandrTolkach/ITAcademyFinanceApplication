package by.tolkach.mailScheduler.dto;

import by.tolkach.mailScheduler.dto.scheduledMail.Essence;
import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeDeserializer;
import by.tolkach.mailScheduler.dto.serializer.LongLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"uuid"})
public class Schedule extends Essence {

    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime startTime;
    @JsonSerialize(using = LongLocalDateTimeSerializer.class)
    @JsonDeserialize(using = LongLocalDateTimeDeserializer.class)
    private LocalDateTime stopTime;
    private int interval;
    private ScheduleTimeUnit timeUnit;

    public Schedule() {
    }

    public Schedule(UUID uuid, LocalDateTime startTime, LocalDateTime stopTime,
                    int interval, ScheduleTimeUnit timeUnit) {
        super(uuid);
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.interval = interval;
        this.timeUnit = timeUnit;
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

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
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
        private int interval;
        private ScheduleTimeUnit timeUnit;

        private Builder() {
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

        public Builder setInterval(int interval) {
            this.interval = interval;
            return this;
        }

        public Builder setTimeUnit(ScheduleTimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public Schedule build() {
            return new Schedule(uuid, startTime, stopTime, interval, timeUnit);
        }
    }
}
