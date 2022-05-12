package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dto.scheduledOperation.Schedule;
import by.tolkach.schedulerAccount.service.api.IValidationService;
import by.tolkach.schedulerAccount.dto.exception.MultipleErrorsException;
import by.tolkach.schedulerAccount.dto.exception.SingleError;
import org.springframework.stereotype.Service;

@Service
public class ScheduleValidationService implements IValidationService<Schedule> {

    @Override
    public Schedule validate(Schedule schedule) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (schedule.getStartTime() == null) {
            validationException.add(new SingleError("start_time", "Необходимо указать начальное время."));
        }

        if (schedule.getStopTime() == null) {
            validationException.add(new SingleError("stop_time", "Необходимо указать конечное время"));
        }

        if (validationException.getErrorCount() > 0) {
            throw validationException;
        }
        return schedule;
    }
}
