package by.tolkach.account.service.rest.object.converter;

import by.tolkach.account.dto.operation.OperationCategory;
import by.tolkach.account.service.rest.object.OperationCategoryRestObject;
import org.springframework.stereotype.Component;

@Component
public class OperationCategoryRestConverter implements IRestObjectConverter<OperationCategory, OperationCategoryRestObject> {

    @Override
    public OperationCategoryRestObject toRestObject(OperationCategory currency) {
        return OperationCategoryRestObject.Builder.createBuilder()
                .setUuid(currency.getUuid())
                .setDtCreate(currency.getDtCreate())
                .setDtUpdate(currency.getDtUpdate())
                .setTitle(currency.getTitle())
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
