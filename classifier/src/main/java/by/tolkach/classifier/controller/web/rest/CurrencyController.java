package by.tolkach.classifier.controller.web.rest;

import by.tolkach.classifier.controller.web.PageChecker;
import by.tolkach.classifier.dto.Currency;
import by.tolkach.classifier.service.api.ICurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/classifier/currency")
public class CurrencyController {

    private final ICurrencyService currencyService;

    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page", required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.currencyService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@PathVariable(name = "uuid") UUID currencyId) {
        return ResponseEntity.ok(this.currencyService.read(currencyId));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody(required = false) Currency currency) {
        this.currencyService.create(currency);
        return ResponseEntity.ok("Валюта добвалена в справочник");
    }
}
