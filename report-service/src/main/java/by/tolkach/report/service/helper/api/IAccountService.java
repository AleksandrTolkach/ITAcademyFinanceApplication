package by.tolkach.report.service.helper.api;

import by.tolkach.report.dto.Account;

import java.util.UUID;

public interface IAccountService {
    Account read(UUID accountId);
    Account save(Account account);
    Account update(Account account);
}
