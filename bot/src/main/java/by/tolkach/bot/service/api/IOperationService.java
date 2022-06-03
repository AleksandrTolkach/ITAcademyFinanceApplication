package by.tolkach.bot.service.api;

import by.tolkach.bot.dto.Operation;

import java.util.UUID;

public interface IOperationService {
    Operation save(Operation operation);
    Operation read(UUID operation);
}
