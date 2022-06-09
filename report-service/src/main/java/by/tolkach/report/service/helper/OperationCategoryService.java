package by.tolkach.report.service.helper;

import by.tolkach.report.dao.api.entity.converter.IEntityConverter;
import by.tolkach.report.dao.api.helper.IOperationCategoryStorage;
import by.tolkach.report.dao.api.helper.entity.OperationCategoryEntity;
import by.tolkach.report.dto.operation.OperationCategory;
import by.tolkach.report.service.helper.api.IOperationCategoryService;
import by.tolkach.report.service.helper.api.OperationCategories;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OperationCategoryService implements IOperationCategoryService {

    private final IOperationCategoryStorage operationCategoryStorage;
    private final IEntityConverter<OperationCategory, OperationCategoryEntity> operationCategoryEntityConverter;

    public OperationCategoryService(IOperationCategoryStorage operationCategoryStorage,
                                    IEntityConverter<OperationCategory, OperationCategoryEntity> operationCategoryEntityConverter) {
        this.operationCategoryStorage = operationCategoryStorage;
        this.operationCategoryEntityConverter = operationCategoryEntityConverter;
    }

    @Override
    public OperationCategory save(OperationCategory operationCategory) {
        OperationCategoryEntity operationCategoryEntity =
                this.operationCategoryEntityConverter.toEntity(operationCategory);
        operationCategoryEntity = this.operationCategoryStorage.save(operationCategoryEntity);
        return this.operationCategoryEntityConverter.toDto(operationCategoryEntity);
    }

    @Override
    public OperationCategory read(UUID operationCategoryId) {
        OperationCategoryEntity operationCategoryEntity =
                this.operationCategoryStorage.findById(operationCategoryId).orElse(null);
        OperationCategories.isExistingCategory(operationCategoryEntity);
        return this.operationCategoryEntityConverter.toDto(operationCategoryEntity);
    }

    @Override
    public OperationCategory read(String title) {
        OperationCategoryEntity operationCategoryEntity = this.operationCategoryStorage.findByTitle(title);
        OperationCategories.isExistingCategory(operationCategoryEntity);
        return this.operationCategoryEntityConverter.toDto(operationCategoryEntity);
    }

    @Override
    public OperationCategory update(OperationCategory operationCategory) {
        OperationCategoryEntity operationCategoryEntity =
                this.operationCategoryEntityConverter.toEntity(operationCategory);
        OperationCategories.updateOperationCategoryParameters(operationCategory, operationCategoryEntity);
        this.operationCategoryStorage.save(operationCategoryEntity);
        return this.operationCategoryEntityConverter.toDto(operationCategoryEntity);
    }
}
