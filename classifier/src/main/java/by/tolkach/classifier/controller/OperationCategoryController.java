package by.tolkach.classifier.controller;

import by.tolkach.classifier.dto.OperationCategory;
import by.tolkach.classifier.dto.SimplePageable;
import by.tolkach.classifier.service.api.IOperationCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classifier/operation/category")
public class OperationCategoryController {

    private final IOperationCategoryService operationCategoryService;

    public OperationCategoryController(IOperationCategoryService operationCategoryService) {
        this.operationCategoryService = operationCategoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page") Integer page,
                                   @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(this.operationCategoryService.read(new SimplePageable(page, size)));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody OperationCategory operationCategory) {
        this.operationCategoryService.create(operationCategory);
        return ResponseEntity.ok("Категория добавлена в справочник");
    }
}
