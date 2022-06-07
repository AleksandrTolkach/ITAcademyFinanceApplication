package by.tolkach.account.service.rest.object.converter;
import by.tolkach.account.dto.account.Account;
import by.tolkach.account.service.rest.object.AccountRestObject;
import org.springframework.stereotype.Component;

@Component
public class AccountRestObjectConverter implements IRestObjectConverter<Account, AccountRestObject> {

    @Override
    public AccountRestObject toRestObject(Account account) {
        return AccountRestObject.Builder.createBuilder()
                .setUuid(account.getUuid())
                .setTitle(account.getTitle())
                .setDescription(account.getDescription())
                .setDtCreate(account.getDtCreate())
                .setDtUpdate(account.getDtUpdate())
                .build();
    }

    @Override
    public Account toDto(AccountRestObject entity) {
        return Account.Builder.createBuilder()
                .setUuid(entity.getUuid())
                .setTitle(entity.getTitle())
                .setDescription(entity.getDescription())
                .setDtCreate(entity.getDtCreate())
                .setDtUpdate(entity.getDtUpdate())
                .build();
    }
}
