package by.tolkach.account.service.account;
import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;
import by.tolkach.account.service.account.api.IAccountService;
import by.tolkach.account.service.account.api.IBalanceService;
import by.tolkach.account.service.api.IValidationService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Primary
public class AccountDecoratorService implements IAccountService {

    private final AccountService accountService;
    private final IValidationService<Account> accountValidationService;
    private final IBalanceService balanceService;

    public AccountDecoratorService(AccountService accountService,
                                   IValidationService<Account> accountValidationService,
                                   IBalanceService balanceService) {
        this.accountService = accountService;
        this.accountValidationService = accountValidationService;
        this.balanceService = balanceService;
    }

    @Override
    public Account create(Account account) {
        this.accountValidationService.validate(account);
        account.setDtCreate(LocalDateTime.now().withNano(0));
        account.setDtUpdate(account.getDtCreate());
        Account createdAccount = this.accountService.create(account);
        this.balanceService.create(createdAccount);
        return createdAccount;
    }

    @Override
    public Page<Account> read(SimplePageable pageable) {
        Page<Account> page = this.accountService.read(pageable);
        List<Account> accounts = page.getContent();
        for (Account account: accounts) {
            account.setBalance(BigDecimal.valueOf(this.balanceService.read(account.getUuid()).getSum()));
        }
        return page;
    }

    @Override
    public Account read(UUID id) {
        Account account = this.accountService.read(id);
        account.setBalance(BigDecimal.valueOf(this.balanceService.read(id).getSum()));
        return account;
    }

    @Override
    public Account update(UUID id, LocalDateTime dtUpdate, Account account) {
        this.accountValidationService.validate(account);
        return this.accountService.update(id, dtUpdate, account);
    }
}
