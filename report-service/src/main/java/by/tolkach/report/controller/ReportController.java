package by.tolkach.report.controller;

import by.tolkach.report.dto.ReportType;
import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.reportParam.ExtendedParam;
import by.tolkach.report.service.api.IReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
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
        this.reportService.create(extendedParam, type);
        return ResponseEntity.ok("Отчет запущен");
    }
}
