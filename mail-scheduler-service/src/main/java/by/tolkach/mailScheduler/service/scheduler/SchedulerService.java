package by.tolkach.mailScheduler.service.scheduler;

import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.exception.ScheduleException;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.scheduler.api.ISchedulerService;
import org.quartz.*;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;

@Service
public class SchedulerService implements ISchedulerService {

    private final static String ZONE_OFFSET = "+03:00";
    private final static String JOB_GROUP = "mails";
    private final Scheduler scheduler;

    public SchedulerService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void create(UUID mailId, UUID paramId, ReportType reportType, Schedule schedule) {
        JobDetail job = JobBuilder.newJob(CreateMailJob.class)
                .withIdentity(mailId.toString(), JOB_GROUP)
                .usingJobData("mail", mailId.toString())
                .usingJobData("param", paramId.toString())
                .usingJobData("reportType", reportType.name())
                .usingJobData("schedule", schedule.getUuid().toString())
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(mailId.toString(), JOB_GROUP)
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
    public void update(UUID mailId, Schedule schedule) {

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(mailId.toString(), JOB_GROUP)
                .startAt(Date.from(schedule.getStartTime().toInstant(ZoneOffset.of(ZONE_OFFSET))))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds((int) schedule.getTimeUnit().toSeconds(schedule.getInterval()))
                        .repeatForever())
                .endAt(Date.from(schedule.getStopTime().toInstant(ZoneOffset.of(ZONE_OFFSET))))
                .build();

        TriggerKey mail = TriggerKey.triggerKey(mailId.toString(), JOB_GROUP);
        try {
            this.scheduler.rescheduleJob(mail, trigger);
        } catch (SchedulerException e) {
            throw new ScheduleException("Ошибка при обновлении расписания.");
        }
    }
}
