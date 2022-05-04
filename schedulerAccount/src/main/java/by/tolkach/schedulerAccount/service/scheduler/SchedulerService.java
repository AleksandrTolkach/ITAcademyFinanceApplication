package by.tolkach.schedulerAccount.service.scheduler;

import by.tolkach.schedulerAccount.dto.Schedule;
import by.tolkach.schedulerAccount.service.scheduler.api.ISchedulerService;
import org.quartz.*;
import org.quartz.impl.SchedulerRepository;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class SchedulerService implements ISchedulerService {

    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void create(UUID operationId, Schedule schedule) {
        JobDetail job = JobBuilder.newJob(CreateOperationJob.class)
                .withIdentity(operationId.toString(), "operations")
                .usingJobData("operation", operationId.toString())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(operationId.toString(), "operations")
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

    @Override
    public void update(UUID operationId, Schedule schedule) {

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(operationId.toString(), "operations")
                .startAt(Date.from(schedule.getStartTime().toInstant(ZoneOffset.of("+03:00"))))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds((int) schedule.getTimeUnit().toSeconds(schedule.getInterval()))
                        .repeatForever())
                .endAt(Date.from(schedule.getStopTime().toInstant(ZoneOffset.of("+03:00"))))
                .build();

        TriggerKey operations = TriggerKey.triggerKey(operationId.toString(), "operations");
        try {
            this.scheduler.rescheduleJob(operations, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
