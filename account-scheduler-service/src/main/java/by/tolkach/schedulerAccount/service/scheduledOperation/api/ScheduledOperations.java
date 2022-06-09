package by.tolkach.schedulerAccount.service.scheduledOperation.api;

import by.tolkach.schedulerAccount.dto.scheduledOperation.Operation;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduledOperation;

import java.time.LocalDateTime;
import java.util.UUID;

public class ScheduledOperations {

    private ScheduledOperations() {
    }

    public static ScheduledOperation createParameters(Schedule schedule, Operation operation) {
        LocalDateTime dtCreate = LocalDateTime.now();
        return ScheduledOperation.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setSchedule(schedule)
                .setOperation(operation)
                .build();
    }
}
