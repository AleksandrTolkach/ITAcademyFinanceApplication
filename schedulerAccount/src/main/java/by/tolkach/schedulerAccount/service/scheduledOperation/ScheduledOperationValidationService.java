package by.tolkach.schedulerAccount.service.scheduledOperation;

import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduledOperation;
import by.tolkach.schedulerAccount.service.api.IValidationService;
import by.tolkach.schedulerAccount.dto.exception.MultipleErrorsException;
import by.tolkach.schedulerAccount.dto.exception.SingleError;
import org.springframework.stereotype.Service;

@Service
public class ScheduledOperationValidationService implements IValidationService<ScheduledOperation> {

    @Override
    public ScheduledOperation validate(ScheduledOperation scheduledOperation) {
        MultipleErrorsException validationException = new MultipleErrorsException();
        if (scheduledOperation.getSchedule() == null) {
            validationException.add(new SingleError("schedule", "Необходимо добавить расписание"));
        }

        if (scheduledOperation.getOperation() == null) {
            validationException.add(new SingleError("operation", "Необходимо добавить операцию"));
        }
        return scheduledOperation;
    }
}
