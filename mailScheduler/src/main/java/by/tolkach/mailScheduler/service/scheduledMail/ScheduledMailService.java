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
import by.tolkach.mailScheduler.service.api.IParamValidationService;
import by.tolkach.mailScheduler.service.api.IValidationService;
import by.tolkach.mailScheduler.service.api.Pagination;
import by.tolkach.mailScheduler.dto.exception.NotFoundException;
import by.tolkach.mailScheduler.service.scheduledMail.api.*;
import by.tolkach.mailScheduler.service.scheduler.api.ISchedulerService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ScheduledMailService implements IScheduledMailService {

    private final IScheduledMailStorage scheduledMailStorage;
    private final IEntityConverter<ScheduledMail, ScheduledMailEntity> scheduledMailEntityConverter;
    private final IMailService mailService;
    private final IParamService paramService;
    private final IScheduleService scheduleService;
    private final ISchedulerService schedulerService;
    private final IValidationService<Mail> mailValidationService;
    private final IParamValidationService paramValidationService;
    private final IValidationService<Schedule> scheduleValidationService;

    public ScheduledMailService(IScheduledMailStorage scheduledMailStorage,
                                IEntityConverter<ScheduledMail, ScheduledMailEntity> scheduledMailEntityConverter,
                                IMailService mailService,
                                IParamService paramService,
                                IScheduleService scheduleService,
                                ISchedulerService schedulerService,
                                IValidationService<Mail> mailValidationService,
                                IParamValidationService paramValidationService,
                                IValidationService<Schedule> scheduleValidationService) {
        this.scheduledMailStorage = scheduledMailStorage;
        this.scheduledMailEntityConverter = scheduledMailEntityConverter;
        this.mailService = mailService;
        this.paramService = paramService;
        this.scheduleService = scheduleService;
        this.schedulerService = schedulerService;
        this.mailValidationService = mailValidationService;
        this.paramValidationService = paramValidationService;
        this.scheduleValidationService = scheduleValidationService;
    }

    @Override
    public ScheduledMail create(Mail mail, Param param, ReportType reportType, Schedule schedule) {
        this.mailValidationService.validate(mail);
        this.paramValidationService.validate(param, reportType);
        this.scheduleValidationService.validate(schedule);
        mail = this.mailService.create(mail);
        param.setFrom(schedule.getStartTime().minusSeconds(schedule.getTimeUnit().toSeconds(schedule.getInterval())));
        param.setTo(schedule.getStartTime());
        param = this.paramService.create(param, reportType);
        schedule = this.scheduleService.create(schedule);
        ScheduledMail scheduledMail = ScheduledMails.createParameters(mail, param, schedule);
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

    @Override
    public ScheduledMail update(UUID scheduledMailId, Mail mail, Param param, ReportType reportType, Schedule schedule,
                                LocalDateTime dtUpdate) {
        ScheduledMailEntity scheduledMailEntity = this.scheduledMailStorage.findById(scheduledMailId).orElse(null);
        if (scheduledMailEntity == null) {
            throw new NotFoundException("Запланированной рассылки с таким Id не существует.");
        }

        LocalDateTime dtUpdate1 = scheduledMailEntity.getDtUpdate();
        if (!dtUpdate1.equals(dtUpdate)) {
            throw new NotFoundException("Запись устарела. Пожалуйста, обновите запрос.");
        }
        this.mailValidationService.validate(mail);
        this.paramValidationService.validate(param, reportType);
        this.scheduleValidationService.validate(schedule);
        ScheduledMail scheduledMail = this.scheduledMailEntityConverter.toDto(scheduledMailEntity);
        mail = this.mailService.update(scheduledMailEntity.getMail().getUuid(), mail);
        param.setFrom(schedule.getStartTime().minusSeconds(schedule.getTimeUnit().toSeconds(schedule.getInterval())));
        param.setTo(schedule.getStartTime());
        param = this.paramService.update(scheduledMailEntity.getParam().getUuid(), param);
        schedule = this.scheduleService.update(scheduledMailEntity.getSchedule().getUuid(), schedule);
        ScheduledMails.updateParameters(scheduledMail, mail, param, schedule);
        this.schedulerService.update(scheduledMailEntity.getMail().getUuid(), schedule);
        scheduledMailEntity = this.scheduledMailEntityConverter.toEntity(scheduledMail);
        scheduledMailEntity = this.scheduledMailStorage.save(scheduledMailEntity);
        return this.scheduledMailEntityConverter.toDto(scheduledMailEntity);
    }
}
