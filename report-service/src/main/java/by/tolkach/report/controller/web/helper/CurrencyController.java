package by.tolkach.report.controller.web.helper;

import by.tolkach.report.dto.operation.Currency;
import by.tolkach.report.service.helper.api.ICurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report/classifier/currency")
public class CurrencyController {

    private final ICurrencyService currencyService;

    public CurrencyController(ICurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody Currency currency) {
        this.currencyService.save(currency);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Currency currency) {
        this.currencyService.update(currency);
        return ResponseEntity.ok().build();
    }
}
