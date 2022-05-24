package by.tolkach.report.controller;

import by.tolkach.report.dto.SimplePageable;
import by.tolkach.report.dto.report.ReportType;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.service.api.IReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @RequestMapping(value = "/{uuid}/export", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> export(@PathVariable(name = "uuid") UUID reportId) {
        MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="
                        + reportId + ".xlsx")
                .contentType(mediaType)
                .body(this.reportService.read(reportId).toByteArray());
    }

    @RequestMapping(value = "/{uuid}/export", method = RequestMethod.HEAD)
    public ResponseEntity<?> head(@PathVariable(name = "uuid") UUID reportId) {
        this.reportService.read(reportId);
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable(name = "type") ReportType type,
                                    @RequestBody Param param) {
        this.reportService.create(param, type);
        return ResponseEntity.ok("Отчет запущен");
    }
}
