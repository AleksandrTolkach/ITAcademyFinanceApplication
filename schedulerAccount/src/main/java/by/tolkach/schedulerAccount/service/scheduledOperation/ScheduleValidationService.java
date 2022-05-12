package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.service.api.IValidationService;
import by.tolkach.schedulerAccount.dto.exception.MultipleErrorsException;
import by.tolkach.schedulerAccount.dto.exception.SingleError;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleValidationService implements IValidationService<Schedule> {

    @Override
    public Schedule validate(Schedule schedule) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (schedule.getStartTime() == null) {
            validationException.add(new SingleError("start_time", "Необходимо указать начальное время."));
        } else if (schedule.getStartTime().compareTo(LocalDateTime.now()) < 0) {
            validationException.add(new SingleError("start_time", "Указано неверное начальное время."));
        }

        if (schedule.getStopTime() == null) {
            validationException.add(new SingleError("stop_time", "Необходимо указать конечное время."));
        } else if (schedule.getStopTime().compareTo(LocalDateTime.now()) < 0) {
            validationException.add(new SingleError("stop_time", "Указано неверное конечное время."));
        }

        if (schedule.getStopTime().compareTo(schedule.getStartTime()) < 0) {
            validationException.add(new SingleError("stop_time", "Неверно указан промежуток времени."));
        }

        if (schedule.getInterval() <= 0) {
            validationException.add(new SingleError("interval", "Неверно указан интервал."));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return schedule;
    }
}
