package by.tolkach.mailScheduler.service.scheduler;

import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.rest.api.IMailRestClientService;
import by.tolkach.mailScheduler.service.scheduledMail.api.IMailService;
import by.tolkach.mailScheduler.service.scheduledMail.api.IParamService;
import by.tolkach.mailScheduler.service.scheduledMail.api.IScheduleService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.UUID;

public class CreateMailJob implements Job {

    private final IMailRestClientService mailRestClientService;
    private final IMailService mailService;
    private final IParamService paramService;
    private final IScheduleService scheduleService;

    public CreateMailJob(IMailRestClientService mailRestClientService,
                         IMailService mailService,
                         IParamService paramService,
                         IScheduleService scheduleService) {
        this.mailRestClientService = mailRestClientService;
        this.mailService = mailService;
        this.paramService = paramService;
        this.scheduleService = scheduleService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        UUID mailId = UUID.fromString(context.getMergedJobDataMap().getString("mail"));
        UUID paramId = UUID.fromString(context.getMergedJobDataMap().getString("param"));
        ReportType reportType = ReportType.valueOf(context.getMergedJobDataMap().getString("reportType"));
        UUID scheduleId = UUID.fromString(context.getMergedJobDataMap().getString("schedule"));

        Mail mail = this.mailService.read(mailId);
        Param param = this.paramService.read(paramId);
        Schedule schedule = this.scheduleService.read(scheduleId);
        this.mailRestClientService.create(mail, param, reportType);

        if (!reportType.name().equals(ReportType.BALANCE.name())) {
            long interval = schedule.getTimeUnit().toSeconds(schedule.getInterval());
            param.setFrom(param.getFrom().plusSeconds(interval));
            param.setTo(param.getTo().plusSeconds(interval));
        }

        this.paramService.update(paramId, param);
    }
}
