package by.tolkach.account.service.account.api;

import by.tolkach.account.dto.account.Account;
import by.tolkach.account.dto.Page;
import by.tolkach.account.dto.SimplePageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAccountService {
    Account create(Account account);
    Page<Account> read(SimplePageable pageable);
    Account read(UUID id);
    Account update(UUID id , LocalDateTime dtUpdate, Account account);
}
