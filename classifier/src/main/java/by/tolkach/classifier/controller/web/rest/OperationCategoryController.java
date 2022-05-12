package by.tolkach.classifier.controller.web.rest;

import by.tolkach.classifier.controller.web.PageChecker;
import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.service.api.IOperationCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/classifier/operation/category")
public class OperationCategoryController {

    private final IOperationCategoryService operationCategoryService;

    public OperationCategoryController(IOperationCategoryService operationCategoryService) {
        this.operationCategoryService = operationCategoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page", required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.operationCategoryService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(value = "/{operation_category_uuid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@PathVariable(name = "operation_category_uuid") UUID operationCategoryId) {
        return ResponseEntity.ok(this.operationCategoryService.read(operationCategoryId));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody(required = false) OperationCategory operationCategory) {
        this.operationCategoryService.create(operationCategory);
        return ResponseEntity.ok("Категория добавлена в справочник");
    }
}
