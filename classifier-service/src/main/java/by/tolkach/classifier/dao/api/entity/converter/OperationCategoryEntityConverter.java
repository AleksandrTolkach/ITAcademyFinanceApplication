package by.tolkach.classifier.dao.api.entity.converter;

import by.tolkach.classifier.dao.api.entity.OperationCategoryEntity;
import by.tolkach.classifier.dto.OperationCategory;
import org.springframework.stereotype.Component;

@Component
public class OperationCategoryEntityConverter implements IEntityConverter<OperationCategory, OperationCategoryEntity> {
    @Override
    public OperationCategoryEntity toEntity(OperationCategory operationCategory) {
        return OperationCategoryEntity.Builder.createBuilder()
                .setUuid(operationCategory.getUuid())
                .setDtCreate(operationCategory.getDtCreate())
                .setDtUpdate(operationCategory.getDtUpdate())
                .setTitle(operationCategory.getTitle())
                .build();
    }

    @Override
    public OperationCategory toDto(OperationCategoryEntity entity) {
        return OperationCategory.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setTitle(entity.getTitle())
                .build();
    }
}
