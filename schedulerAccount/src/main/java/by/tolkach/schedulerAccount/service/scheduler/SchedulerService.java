package by.tolkach.schedulerAccount.service.scheduler;

import by.tolkach.schedulerAccount.dto.Schedule;
import by.tolkach.schedulerAccount.dto.ScheduleTimeUnit;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class SchedulerService {

    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void create(UUID operation, Schedule schedule) {
        JobDetail job = JobBuilder.newJob(CreateOperationJob.class)
                .withIdentity(operation.toString(), "operations")
                .usingJobData("operation", operation.toString())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(operation.toString(), "operations")
                .startAt(Date.from(schedule.getStartTime().toInstant(ZoneOffset.of("+03:00"))))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds((int) schedule.getTimeUnit().toSeconds(schedule.getInterval()))
                        .repeatForever())
                .endAt(Date.from(schedule.getStopTime().toInstant(ZoneOffset.of("+03:00"))))
                .build();

        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
