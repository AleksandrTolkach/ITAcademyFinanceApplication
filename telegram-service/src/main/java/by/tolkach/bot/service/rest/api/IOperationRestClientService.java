package by.tolkach.bot.service.rest.api;

import by.tolkach.bot.dto.Operation;

import java.util.UUID;

public interface IOperationRestClientService {
    String create(Operation operation);
    Operation read(UUID operationId, UUID accountId);
    String update(Operation operation);
}
