package by.tolkach.classifier.service.classifier;

import by.tolkach.classifier.dao.api.IOperationCategoryStorage;
import by.tolkach.classifier.dao.api.entity.OperationCategoryEntity;
import by.tolkach.classifier.dao.api.entity.converter.IEntityConverter;
import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;
import by.tolkach.classifier.service.classifier.api.IOperationCategoryService;
import by.tolkach.classifier.service.classifier.api.IValidationService;
import by.tolkach.classifier.service.classifier.api.Pagination;
import by.tolkach.classifier.service.classifier.api.exception.NotFoundError;
import by.tolkach.classifier.service.rest.api.IReportRestClientService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class OperationCategoryService implements IOperationCategoryService {

    private final IOperationCategoryStorage operationCategoryStorage;
    private final IEntityConverter<OperationCategory, OperationCategoryEntity> operationCategoryEntityConverter;
    private final IValidationService<OperationCategory> operationCategoryValidationService;
    private final IReportRestClientService reportRestClientService;

    public OperationCategoryService(IOperationCategoryStorage operationCategoryStorage,
                                    IEntityConverter<OperationCategory, OperationCategoryEntity> operationCategoryEntityConverter,
                                    IValidationService<OperationCategory> operationCategoryValidationService,
                                    IReportRestClientService reportRestClientService) {
        this.operationCategoryStorage = operationCategoryStorage;
        this.operationCategoryEntityConverter = operationCategoryEntityConverter;
        this.operationCategoryValidationService = operationCategoryValidationService;
        this.reportRestClientService = reportRestClientService;
    }

    @Override
    public OperationCategory create(OperationCategory operationCategory) {
        this.operationCategoryValidationService.validate(operationCategory);
        this.createOperationCategoryParameters(operationCategory);
        OperationCategoryEntity operationCategoryEntity = this.operationCategoryEntityConverter.toEntity(operationCategory);
        operationCategory = this.operationCategoryEntityConverter
                .toDto(this.operationCategoryStorage.save(operationCategoryEntity));
        this.reportRestClientService.sendOperationCategory(operationCategory);
        return this.operationCategoryEntityConverter.toDto(this.operationCategoryStorage.save(operationCategoryEntity));
    }

    @Override
    public Page<OperationCategory> read(SimplePageable pageable) {
        List<OperationCategoryEntity> operationCategoryEntities =
                this.operationCategoryStorage.findAllBy(PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(OperationCategory.class, OperationCategoryEntity.class).properties(operationCategoryEntities, pageable,
                this.operationCategoryStorage.count(), this.operationCategoryEntityConverter);
    }

    @Override
    public OperationCategory read(UUID operationCategoryId) {
        OperationCategoryEntity operationCategoryEntity =
                this.operationCategoryStorage.findById(operationCategoryId).orElse(null);
        if (operationCategoryEntity == null) {
            throw new NotFoundError("Категории с таким ID не существует.");
        }
        return this.operationCategoryEntityConverter.toDto(operationCategoryEntity);
    }

    @Override
    public List<OperationCategory> read() {
        List<OperationCategoryEntity> categoryEntities = this.operationCategoryStorage.findAllBy();
        List<OperationCategory> operationCategories = new ArrayList<>();
        for (OperationCategoryEntity operationCategoryEntity: categoryEntities) {
            operationCategories.add(this.operationCategoryEntityConverter.toDto(operationCategoryEntity));
        }
        return operationCategories;
    }

    @Override
    public OperationCategory read(String title) {
        OperationCategoryEntity operationCategoryEntity = this.operationCategoryStorage.findByTitle(title);
        return this.operationCategoryEntityConverter.toDto(operationCategoryEntity);
    }

    public OperationCategory createOperationCategoryParameters(OperationCategory operationCategory) {
        LocalDateTime createdTime = LocalDateTime.now();
        operationCategory.setDtCreate(createdTime);
        operationCategory.setDtUpdate(createdTime);
        return operationCategory;
    }
}
