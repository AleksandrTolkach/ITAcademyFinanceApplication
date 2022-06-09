package by.tolkach.mailScheduler.dao.api.entity.converter.attributesConverter;

import by.tolkach.mailScheduler.dao.api.entity.AccountsEntity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Converter
public class AccountsAttributeConverter implements AttributeConverter<AccountsEntity, String> {

    private static final String SEPARATOR = ", ";

    @Override
    public String convertToDatabaseColumn(AccountsEntity attribute) {
        if (attribute == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        if (attribute.getAccounts() != null && !attribute.getAccounts().isEmpty()) {
            for (UUID uuid: attribute.getAccounts()) {
                sb.append(uuid);
                sb.append(SEPARATOR);
            }
        }
        return sb.toString();
    }

    @Override
    public AccountsEntity convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }

        String[] pieces = dbData.split(SEPARATOR);

        if (pieces == null || pieces.length == 0) {
            return null;
        }

        AccountsEntity accountsEntity = new AccountsEntity();
        List<UUID> uuids = new ArrayList<>();
        for (int i = 0; i < pieces.length; i++) {
            uuids.add(UUID.fromString(pieces[i]));
        }
        accountsEntity.setAccounts(uuids);

        return accountsEntity;
    }
}
