package by.tolkach.mailScheduler.service.scheduler;

import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.scheduler.api.ISchedulerService;
import org.quartz.*;
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

    public void create(UUID mailId, UUID paramId, ReportType reportType, Schedule schedule) {
        JobDetail job = JobBuilder.newJob(CreateOperationJob.class)
                .withIdentity(mailId.toString(), "mails")
                .usingJobData("mail", mailId.toString())
                .usingJobData("param", paramId.toString())
                .usingJobData("reportType", reportType.name())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(mailId.toString(), "mails")
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
    public void update(UUID mailId, Schedule schedule) {

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(mailId.toString(), "mails")
                .startAt(Date.from(schedule.getStartTime().toInstant(ZoneOffset.of("+03:00"))))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds((int) schedule.getTimeUnit().toSeconds(schedule.getInterval()))
                        .repeatForever())
                .endAt(Date.from(schedule.getStopTime().toInstant(ZoneOffset.of("+03:00"))))
                .build();

        TriggerKey mail = TriggerKey.triggerKey(mailId.toString(), "mails");
        try {
            this.scheduler.rescheduleJob(mail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
