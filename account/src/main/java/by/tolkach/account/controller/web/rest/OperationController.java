package by.tolkach.account.controller.web.rest;

import by.tolkach.account.dto.Operation;
import by.tolkach.account.dto.SimplePageable;
import by.tolkach.account.service.api.IOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
@RequestMapping("/account/{id}/operation")
public class OperationController {

    private final IOperationService operationService;

    public OperationController(IOperationService operationService) {
        this.operationService = operationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@PathVariable UUID id, @RequestParam(name = "page", required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.operationService.read(id, new SimplePageable(page, size)));
    }

    @RequestMapping(value = "/{uuid_operation}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@PathVariable(name = "id") UUID accountId,
                                   @PathVariable(name = "uuid_operation") UUID operationId) {
        return ResponseEntity.ok(this.operationService.read(operationId, accountId));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable UUID id,
                                    @RequestBody Operation operation) {
        this.operationService.create(operation, id);
        return ResponseEntity.ok("Операция добавлена к счету");
    }

    @RequestMapping(value = "/{uuid_operation}/dt_update/{dt_update}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(name = "id") UUID accountId,
                                    @PathVariable(name = "uuid_operation") UUID operationId,
                                    @PathVariable(name = "dt_update") Long dtUpdate,
                                    @RequestBody Operation operation) {
        this.operationService.update(accountId, operationId,
                LocalDateTime.ofEpochSecond(dtUpdate, 0, ZoneOffset.UTC), operation);
        return ResponseEntity.ok("Операция изменена");
    }
}
