package by.tolkach.schedulerAccount.service.scheduler;

import by.tolkach.schedulerAccount.service.rest.OperationRestClientService;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IOperationService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.UUID;

public class CreateOperationJob implements Job {

    private final OperationRestClientService restClientService;
    private final IOperationService operationService;

    public CreateOperationJob(OperationRestClientService restClientService, IOperationService operationService) {
        this.restClientService = restClientService;
        this.operationService = operationService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String uuid = context.getMergedJobDataMap().getString("operation");
        this.restClientService.create(this.operationService.read(UUID.fromString(uuid)));
    }
}
