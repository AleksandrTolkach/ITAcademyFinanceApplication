package by.tolkach.mailScheduler.service;

import by.tolkach.mailScheduler.dto.exception.MultipleErrorsException;
import by.tolkach.mailScheduler.dto.exception.SingleError;
import by.tolkach.mailScheduler.dto.Schedule;
import by.tolkach.mailScheduler.service.api.IValidationService;
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

        if (schedule.getInterval() <= 0) {
            validationException.add(new SingleError("interval", "Неверно указан интервал."));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return schedule;
    }
}
