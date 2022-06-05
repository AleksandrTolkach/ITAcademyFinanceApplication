package by.tolkach.schedulerAccount.controller.advice;

import by.tolkach.schedulerAccount.dto.exception.MultipleErrorsException;
import by.tolkach.schedulerAccount.dto.exception.NotFoundException;
import by.tolkach.schedulerAccount.dto.exception.SingleError;
import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduleAndOperationWrapper;
import org.springframework.stereotype.Component;

@Component
public class WrapperValidation {

    public ScheduleAndOperationWrapper validate(ScheduleAndOperationWrapper wrapper) {
        MultipleErrorsException multipleErrorsException = new MultipleErrorsException();
        if (wrapper == null) {
            throw new NotFoundException("Необходимо передать объекты расписания и операции.");
        }
        if (wrapper.getSchedule() == null) {
            multipleErrorsException.add(new SingleError("schedule", "Необходимо передать объект расписания."));
        }
        if (wrapper.getOperation() == null) {
            multipleErrorsException.add(new SingleError("operation", "Необходимо передать объект операции"));
        }
        if (multipleErrorsException.getErrorCount() > 0) {
            throw multipleErrorsException;
        }
        return wrapper;
    }
}
