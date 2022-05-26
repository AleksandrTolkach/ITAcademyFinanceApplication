package by.tolkach.mailScheduler.service.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.UUID;

public class CreateOperationJob implements Job {

    private final IOperationRestClientService operationRestClientService;
    private final IOperationService operationService;

    public CreateOperationJob(IOperationRestClientService restClientService, IOperationService operationService) {
        this.operationRestClientService = restClientService;
        this.operationService = operationService;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String uuid = context.getMergedJobDataMap().getString("operation");
        this.operationRestClientService.create(this.operationService.read(UUID.fromString(uuid)));
    }
}
