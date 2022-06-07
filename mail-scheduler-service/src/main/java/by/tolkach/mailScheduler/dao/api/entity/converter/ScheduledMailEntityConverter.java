package by.tolkach.mailScheduler.dao.api.entity.converter;

import by.tolkach.mailScheduler.dao.api.entity.MailEntity;
import by.tolkach.mailScheduler.dao.api.entity.ScheduleEntity;
import by.tolkach.mailScheduler.dao.api.entity.ScheduledMailEntity;
import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.dto.scheduledMail.Mail;
import by.tolkach.mailScheduler.dto.scheduledMail.ScheduledMail;
import org.springframework.stereotype.Component;

@Component
public class ScheduledMailEntityConverter implements IEntityConverter<ScheduledMail, ScheduledMailEntity> {

    private final IEntityConverter<Mail, MailEntity> mailEntityConverter;
    private final IParamEntityConverter paramEntityConverter;
    private final IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter;

    public ScheduledMailEntityConverter(IEntityConverter<Mail, MailEntity> mailEntityConverter,
                                        IParamEntityConverter paramEntityConverter,
                                        IEntityConverter<Schedule, ScheduleEntity> scheduleEntityConverter) {
        this.mailEntityConverter = mailEntityConverter;
        this.paramEntityConverter = paramEntityConverter;
        this.scheduleEntityConverter = scheduleEntityConverter;
    }

    @Override
    public ScheduledMailEntity toEntity(ScheduledMail scheduledMail) {
        return ScheduledMailEntity.Builder.createBuilder()
                .setUuid(scheduledMail.getUuid())
                .setDtCreate(scheduledMail.getDtCreate())
                .setDtUpdate(scheduledMail.getDtUpdate())
                .setMailEntity(this.mailEntityConverter.toEntity(scheduledMail.getMail()))
                .setParamEntity(this.paramEntityConverter.toEntity(scheduledMail.getParam()))
                .setScheduleEntity(this.scheduleEntityConverter.toEntity(scheduledMail.getSchedule()))
                .build();
    }

    @Override
    public ScheduledMail toDto(ScheduledMailEntity entity) {
        return ScheduledMail.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setMail(this.mailEntityConverter.toDto(entity.getMail()))
                .setParam(this.paramEntityConverter.toDto(entity.getParam()))
                .setSchedule(this.scheduleEntityConverter.toDto(entity.getSchedule()))
                .build();
    }
}
