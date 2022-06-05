package by.tolkach.report.controller.web.rest.helper;

import by.tolkach.report.dto.operation.OperationCategory;
import by.tolkach.report.service.helper.api.IOperationCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report/classifier/operation_category")
public class OperationCategoryController {

    public final IOperationCategoryService operationCategoryService;

    public OperationCategoryController(IOperationCategoryService operationCategoryService) {
        this.operationCategoryService = operationCategoryService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody OperationCategory operationCategory) {
        this.operationCategoryService.save(operationCategory);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody OperationCategory operationCategory) {
        this.operationCategoryService.update(operationCategory);
        return ResponseEntity.ok().build();
    }
}
