package by.tolkach.account.controller.web.rest;

import by.tolkach.account.controller.web.PageChecker;
import by.tolkach.account.dto.operation.Operation;
import by.tolkach.account.service.operation.api.IOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
@RequestMapping("/account/{uuid}/operation")
public class OperationController {

    private final IOperationService operationService;

    public OperationController(IOperationService operationService) {
        this.operationService = operationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@PathVariable(name = "uuid") UUID accountId,
                                   @RequestParam(name = "page", required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.operationService.read(accountId, PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(value = "/{uuid_operation}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@PathVariable(name = "uuid") UUID accountId,
                                   @PathVariable(name = "uuid_operation") UUID operationId) {
        return ResponseEntity.ok(this.operationService.read(operationId, accountId));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable(name = "uuid") UUID accountId,
                                    @RequestBody(required = false) Operation operation) {
        this.operationService.create(operation, accountId);
        return ResponseEntity.ok("Операция добавлена к счету");
    }

    @RequestMapping(value = "/{uuid_operation}/dt_update/{dt_update}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(name = "uuid") UUID accountId,
                                    @PathVariable(name = "uuid_operation") UUID operationId,
                                    @PathVariable(name = "dt_update") Long dtUpdate,
                                    @RequestBody(required = false) Operation operation) {
        this.operationService.update(accountId, operationId,
                LocalDateTime.ofEpochSecond(dtUpdate, 0, ZoneOffset.UTC), operation);
        return ResponseEntity.ok("Операция изменена");
    }

    @RequestMapping(value = "/{uuid_operation}/dt_update/{dt_update}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable(name = "uuid") UUID accountID,
                                    @PathVariable(name = "uuid_operation") UUID operationId,
                                    @PathVariable(name = "dt_update") Long dtUpdate) {
        this.operationService.delete(accountID, operationId,
                LocalDateTime.ofEpochSecond(dtUpdate, 0, ZoneOffset.UTC));
        return ResponseEntity.ok("Операция удалена");
    }
}
