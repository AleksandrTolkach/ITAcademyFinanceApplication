package by.tolkach.report.dao.api.entity.attributesConverter;


import by.tolkach.report.dao.api.entity.reportParam.CategoriesEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Converter
public class CategoriesAttributeConverter implements AttributeConverter<CategoriesEntity, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(CategoriesEntity attribute) {
        if (attribute == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (attribute.getCategories() != null && !attribute.getCategories().isEmpty()) {
            for (UUID uuid: attribute.getCategories()) {
                sb.append(uuid);
                sb.append(SEPARATOR);
            }
        }
        return sb.toString();
    }

    @Override
    public CategoriesEntity convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        String[] pieces = dbData.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        CategoriesEntity categoriesEntity = new CategoriesEntity();
        List<UUID> uuids = new ArrayList<>();
        String firstPiece = !pieces[0].isEmpty() ? pieces[0] : null;
        for (int i = 0; i < pieces.length; i++) {
            uuids.add(UUID.fromString(pieces[i]));
        }
        categoriesEntity.setCategories(uuids);

        return categoriesEntity;
    }
}
