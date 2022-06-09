package by.tolkach.schedulerAccount.service.scheduler;

import by.tolkach.schedulerAccount.dto.exception.ScheduleException;
import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.service.scheduler.api.ISchedulerService;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class SchedulerService implements ISchedulerService {

    private static final String JOB_GROUP = "operations";
    private static final String ZONE_OFFSET = "+03:00";

    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void create(UUID operationId, Schedule schedule) {
        JobDetail job = JobBuilder.newJob(CreateOperationJob.class)
                .withIdentity(operationId.toString(), JOB_GROUP)
                .usingJobData("operation", operationId.toString())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(operationId.toString(), JOB_GROUP)
                .startAt(Date.from(schedule.getStartTime().toInstant(ZoneOffset.of(ZONE_OFFSET))))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds((int) schedule.getTimeUnit().toSeconds(schedule.getInterval()))
                        .repeatForever())
                .endAt(Date.from(schedule.getStopTime().toInstant(ZoneOffset.of(ZONE_OFFSET))))
                .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new ScheduleException("Ошибка при создании расписания.");
        }
    }

    @Override
    public void update(UUID operationId, Schedule schedule) {

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(operationId.toString(), JOB_GROUP)
                .startAt(Date.from(schedule.getStartTime().toInstant(ZoneOffset.of(ZONE_OFFSET))))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds((int) schedule.getTimeUnit().toSeconds(schedule.getInterval()))
                        .repeatForever())
                .endAt(Date.from(schedule.getStopTime().toInstant(ZoneOffset.of(ZONE_OFFSET))))
                .build();

        TriggerKey operations = TriggerKey.triggerKey(operationId.toString(), JOB_GROUP);
        try {
            this.scheduler.rescheduleJob(operations, trigger);
        } catch (SchedulerException e) {
            throw new ScheduleException("Ошибка при обновлении расписания.");
        }
    }
}
