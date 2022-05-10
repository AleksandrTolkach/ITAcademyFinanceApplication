package by.tolkach.classifier.service;

import by.tolkach.classifier.dao.api.IOperationCategoryStorage;
import by.tolkach.classifier.dao.api.entity.OperationCategoryEntity;
import by.tolkach.classifier.dao.api.entity.converter.IEntityConverter;
import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.dto.Page;
import by.tolkach.classifier.dto.SimplePageable;
import by.tolkach.classifier.service.api.IOperationCategoryService;
import by.tolkach.classifier.service.api.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OperationCategoryService implements IOperationCategoryService {

    private final IOperationCategoryStorage operationCategoryStorage;
    private final IEntityConverter<OperationCategory, OperationCategoryEntity> operationCategoryEntityConverter;

    public OperationCategoryService(IOperationCategoryStorage operationCategoryStorage, IEntityConverter<OperationCategory, OperationCategoryEntity> operationCategoryEntityConverter) {
        this.operationCategoryStorage = operationCategoryStorage;
        this.operationCategoryEntityConverter = operationCategoryEntityConverter;
    }

    @Override
    public OperationCategory create(OperationCategory operationCategory) {
        LocalDateTime createdTime = LocalDateTime.now().withNano(0);
        operationCategory.setDtCreate(createdTime);
        operationCategory.setDtUpdate(createdTime);
        OperationCategoryEntity operationCategoryEntity = this.operationCategoryEntityConverter.toEntity(operationCategory);
        return this.operationCategoryEntityConverter.toDto(this.operationCategoryStorage.save(operationCategoryEntity));
    }

    @Override
    public OperationCategory read(UUID operationCategoryId) {
        return this.operationCategoryEntityConverter.toDto(this.operationCategoryStorage.findById(operationCategoryId)
                .orElse(null));
    }

    @Override
    public Page<OperationCategory> read(SimplePageable pageable) {
        List<OperationCategoryEntity> operationCategoryEntities =
                this.operationCategoryStorage.findAllBy(PageRequest.of(pageable.getPage(), pageable.getSize()));
        return Pagination.pageOf(OperationCategory.class, OperationCategoryEntity.class).properties(operationCategoryEntities, pageable,
                (int) this.operationCategoryStorage.count(), this.operationCategoryEntityConverter);
    }
}
