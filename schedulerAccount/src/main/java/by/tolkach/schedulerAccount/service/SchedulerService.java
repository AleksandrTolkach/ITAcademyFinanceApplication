package by.tolkach.schedulerAccount.service;

import org.quartz.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SchedulerService {

    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void create(UUID operation) {
        // Define job instance
        JobDetail job1 = JobBuilder.newJob(CreateOperationJob.class)
                .withIdentity(operation.toString(), "operations")
                .usingJobData("operation", operation.toString())
                .build();

// Define a Trigger that will fire "now", and not repeat
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(operation.toString(), "operations")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(10))
                .build();

// Schedule the job with the trigger
        try {
            scheduler.scheduleJob(job1, trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }
}
