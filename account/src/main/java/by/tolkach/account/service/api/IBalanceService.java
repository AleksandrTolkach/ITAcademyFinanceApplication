package by.tolkach.account.service.api;

import by.tolkach.account.dto.Account;
import by.tolkach.account.dto.Balance;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IBalanceService {
    Balance create(Account account);
    Balance read(UUID accountId);
    Balance update(UUID accountId, LocalDateTime dtUpdate, Long num);
}
