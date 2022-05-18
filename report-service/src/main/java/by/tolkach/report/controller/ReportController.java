package by.tolkach.report.controller;

import by.tolkach.report.dto.ReportType;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.service.api.ChoiceReport;
import by.tolkach.report.service.api.IReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    private IReportService reportService;
    private final ChoiceReport choiceReport;

    public ReportController(ChoiceReport choiceReport) {
        this.choiceReport = choiceReport;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page") Integer page,
                                   @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(this.reportService.read(new SimplePageable(page, size)));
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable(name = "type") ReportType type,
                                    @RequestBody ExtendedParam extendedParam) {
        this.reportService = choiceReport.getReportService(type);
        this.reportService.create(extendedParam);
        return ResponseEntity.ok("Отчет запущен");
    }
}
