package by.tolkach.report.controller;

import by.tolkach.report.dto.Operation;
import by.tolkach.report.service.api.IOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/report")
public class OperationController {

    private final IOperationService operationService;

    public OperationController(IOperationService operationService) {
        this.operationService = operationService;
    }

    @RequestMapping(value = "/operation", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> saveOperation(@RequestBody Operation operation) {
        this.operationService.save(operation);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/operation", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> updateOperation(@RequestBody Operation operation) {
        this.operationService.update(operation);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/account/{account_uuid}/operation/{operation_uuid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<?> deleteOperation(@PathVariable(name = "operation_uuid") UUID operationId,
                                             @PathVariable(name = "account_uuid") UUID accountId) {
        this.operationService.delete(operationId, accountId);
        return ResponseEntity.ok().build();
    }
}
