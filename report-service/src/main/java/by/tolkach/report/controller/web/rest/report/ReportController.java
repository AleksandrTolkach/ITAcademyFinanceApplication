package by.tolkach.report.controller.web.rest.report;

import by.tolkach.report.controller.web.PageChecker;
import by.tolkach.report.dto.report.Param;
import by.tolkach.report.dto.report.ReportType;
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
    public ResponseEntity<?> index(@RequestParam(name = "page", required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.reportService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(value = {"//export","/{uuid}/export"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> export(@PathVariable(name = "uuid", required = false) UUID reportId) {
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

    @RequestMapping(value = {"/", "/{type}"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable(name = "type", required = false) ReportType type,
                                    @RequestBody(required = false) Param param) {
        this.reportService.create(param, type);
        return ResponseEntity.ok("Отчет запущен");
    }

    @RequestMapping(value = {"/{type}/mail"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> createMail(@PathVariable(name = "type", required = false) ReportType type,
                                    @RequestBody(required = false) Param param) {
        return ResponseEntity.ok(this.reportService.create(param, type).toByteArray());
    }
}
