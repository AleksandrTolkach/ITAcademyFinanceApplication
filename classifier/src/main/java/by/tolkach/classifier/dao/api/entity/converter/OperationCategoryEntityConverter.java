package by.tolkach.classifier.dao.api.entity.converter;

import by.tolkach.classifier.dao.api.entity.OperationCategoryEntity;
import by.tolkach.classifier.dao.api.entity.builder.OperationCategoryEntityBuilder;
import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.dto.builder.OperationCategoryBuilder;
import org.springframework.stereotype.Component;

@Component
public class OperationCategoryEntityConverter implements IEntityConverter<OperationCategory, OperationCategoryEntity> {
    @Override
    public OperationCategoryEntity toEntity(OperationCategory operationCategory) {
        return OperationCategoryEntityBuilder.createBuilder()
                .setUuid(operationCategory.getUuid())
                .setDtCreate(operationCategory.getDtCreate())
                .setDtUpdate(operationCategory.getDtUpdate())
                .setTitle(operationCategory.getTitle())
                .build();
    }

    @Override
    public OperationCategory toDto(OperationCategoryEntity entity) {
        return OperationCategoryBuilder.createBuilder()
                .setUuid(entity.getUuid())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .setTitle(entity.getTitle())
                .build();
    }
}
