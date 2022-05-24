package by.tolkach.report.service.handler;

import by.tolkach.report.dto.operation.Operation;
import by.tolkach.report.dto.operation.OperationCategory;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.service.helper.api.IOperationCategoryService;
import by.tolkach.report.service.helper.api.IOperationService;
import by.tolkach.report.service.handler.api.IReportHandler;
import by.tolkach.report.service.api.OperationByCategoryComparator;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ByCategoryReportHandler implements IReportHandler {

    private final IOperationService operationService;
    private final IOperationCategoryService operationCategoryService;

    public ByCategoryReportHandler(IOperationService operationService, IOperationCategoryService operationCategoryService) {
        this.operationService = operationService;
        this.operationCategoryService = operationCategoryService;
    }

    @Override
    public List<Operation> getOperations(Param param) {
        List<Operation> operations = this.operationService.read(param);
        List<Operation> filteredOperations = new ArrayList<>();
        for (Operation operation: operations) {
            for (UUID category: param.getCategories()) {
                OperationCategory operationCategoryFromDb = this.operationCategoryService.read(category);
                if (operation.getCategory().compareTo(operationCategoryFromDb.getTitle()) == 0) {
                    filteredOperations.add(operation);
                }
            }
        }
        filteredOperations.sort(new OperationByCategoryComparator());
        return filteredOperations;
    }
}
