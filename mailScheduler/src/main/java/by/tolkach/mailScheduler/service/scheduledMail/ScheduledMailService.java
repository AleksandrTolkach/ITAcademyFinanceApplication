package by.tolkach.mailScheduler.service.scheduledMail;

import by.tolkach.mailScheduler.dao.IScheduledMailStorage;
import by.tolkach.mailScheduler.dao.api.entity.ScheduledMailEntity;
import by.tolkach.mailScheduler.dao.api.entity.converter.IEntityConverter;
import by.tolkach.mailScheduler.dto.Page;
import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.SimplePageable;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.Param;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.dto.scheduledMail.ScheduledMail;
import by.tolkach.mailScheduler.service.api.Pagination;
import by.tolkach.mailScheduler.service.scheduledMail.api.IMailService;
import by.tolkach.mailScheduler.service.scheduledMail.api.IParamService;
import by.tolkach.mailScheduler.service.scheduledMail.api.IScheduleService;
import by.tolkach.mailScheduler.service.scheduledMail.api.IScheduledMailService;
import by.tolkach.mailScheduler.service.scheduler.api.ISchedulerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ScheduledMailService implements IScheduledMailService {

    private final IScheduledMailStorage scheduledMailStorage;
    private final IEntityConverter<ScheduledMail, ScheduledMailEntity> scheduledMailEntityConverter;
    private final IMailService mailService;
    private final IParamService paramService;
    private final IScheduleService scheduleService;
    private final ISchedulerService schedulerService;

    public ScheduledMailService(IScheduledMailStorage scheduledMailStorage,
                                IEntityConverter<ScheduledMail, ScheduledMailEntity> scheduledMailEntityConverter,
                                IMailService mailService,
                                IParamService paramService,
                                IScheduleService scheduleService,
                                ISchedulerService schedulerService) {
        this.scheduledMailStorage = scheduledMailStorage;
        this.scheduledMailEntityConverter = scheduledMailEntityConverter;
        this.mailService = mailService;
        this.paramService = paramService;
        this.scheduleService = scheduleService;
        this.schedulerService = schedulerService;
    }

    @Override
    public ScheduledMail create(Mail mail, Param param, ReportType reportType, Schedule schedule) {
        Mail createdMail = this.mailService.create(mail);
        Param createdParam = this.paramService.create(param, reportType);
        Schedule createdSchedule = this.scheduleService.create(schedule);
        ScheduledMail scheduledMail = this.createScheduledMailParameters(createdMail, createdParam, createdSchedule);
        ScheduledMailEntity scheduledMailEntity = this.scheduledMailEntityConverter.toEntity(scheduledMail);
        scheduledMailEntity = this.scheduledMailStorage.save(scheduledMailEntity);
        this.schedulerService.create(scheduledMail.getMail().getUuid(), scheduledMail.getParam().getUuid(),
                reportType, scheduledMail.getSchedule());
        return this.scheduledMailEntityConverter.toDto(scheduledMailEntity);
    }

    @Override
    public Page<ScheduledMail> read(SimplePageable simplePageable) {
        List<ScheduledMailEntity> scheduledMailEntities =
                this.scheduledMailStorage.findAllBy(PageRequest.of(simplePageable.getPage(), simplePageable.getSize()));
        return Pagination.pageOf(ScheduledMail.class, ScheduledMailEntity.class).properties(scheduledMailEntities,
                simplePageable, this.scheduledMailStorage.count(), this.scheduledMailEntityConverter);
    }


    private ScheduledMail createScheduledMailParameters(Mail mail, Param param, Schedule schedule) {
        LocalDateTime dtCreate = LocalDateTime.now();
        return ScheduledMail.Builder.createBuilder()
                .setUuid(UUID.randomUUID())
                .setDtCreate(dtCreate)
                .setDtUpdate(dtCreate)
                .setMail(mail)
                .setParam(param)
                .setSchedule(schedule)
                .build();
    }
}
