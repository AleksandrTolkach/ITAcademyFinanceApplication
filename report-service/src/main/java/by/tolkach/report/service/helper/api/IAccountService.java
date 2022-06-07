package by.tolkach.report.service.helper.api;

import by.tolkach.report.dto.Account;

public interface IAccountService {
    Account save(Account account);
    Account update(Account account);
}
