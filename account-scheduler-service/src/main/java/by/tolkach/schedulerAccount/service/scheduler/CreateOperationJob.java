package by.tolkach.schedulerAccount.service.scheduler;

import by.tolkach.schedulerAccount.service.rest.api.IAccountRestClientService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IOperationService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.UUID;

public class CreateOperationJob implements Job {

    private final IAccountRestClientService operationRestClientService;
    private final IOperationService operationService;

    public CreateOperationJob(IAccountRestClientService restClientService, IOperationService operationService) {
        this.operationRestClientService = restClientService;
        this.operationService = operationService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String uuid = context.getMergedJobDataMap().getString("operation");
        this.operationRestClientService.createOperation(this.operationService.read(UUID.fromString(uuid)));
    }
}
