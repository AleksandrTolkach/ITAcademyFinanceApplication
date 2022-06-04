package by.tolkach.bot.service.rest.object.converter;

import by.tolkach.bot.dto.OperationCategory;
import by.tolkach.bot.service.rest.object.OperationCategoryRestObject;
import org.springframework.stereotype.Component;

@Component
public class OperationCategoryRestObjectConverter implements IRestObjectConverter<OperationCategory,
        OperationCategoryRestObject> {
    @Override
    public OperationCategoryRestObject toRestObject(OperationCategory operationCategory) {
        return OperationCategoryRestObject.Builder.createBuilder()
                .setUuid(operationCategory.getUuid())
                .setDtCreate(operationCategory.getDtCreate())
                .setDtUpdate(operationCategory.getDtUpdate())
                .setTitle(operationCategory.getTitle())
                .build();
    }

    @Override
    public OperationCategory toDto(OperationCategoryRestObject restObject) {
        return OperationCategory.Builder.createBuilder()
                .setUuid(restObject.getUuid())
                .setDtCreate(restObject.getDtCreate())
                .setDtUpdate(restObject.getDtUpdate())
                .setTitle(restObject.getTitle())
                .build();
    }
}
