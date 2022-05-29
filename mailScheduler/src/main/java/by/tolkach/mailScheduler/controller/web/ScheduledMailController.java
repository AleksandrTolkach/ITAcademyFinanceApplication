package by.tolkach.mailScheduler.controller.web;

import by.tolkach.mailScheduler.dto.scheduledMail.MailParamWrapper;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.scheduledMail.api.IScheduledMailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/scheduler/mail")
public class ScheduledMailController {

    private final IScheduledMailService scheduledMailService;

    public ScheduledMailController(IScheduledMailService scheduledMailService) {
        this.scheduledMailService = scheduledMailService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page", required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.scheduledMailService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@PathVariable(name = "type", required = false) ReportType reportType,
                                    @RequestBody MailParamWrapper mailParamWrapper) {
        this.scheduledMailService.create(mailParamWrapper.getMail(), mailParamWrapper.getParam(),
                reportType, mailParamWrapper.getSchedule());
        return ResponseEntity.ok("Рассылка писем запланирована.");
    }

    @RequestMapping(value = "/{uuid}/{type}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(name = "uuid", required = false) UUID scheduledMailId,
                                    @PathVariable(name = "type", required = false) ReportType reportType,
                                    @RequestBody MailParamWrapper mailParamWrapper) {
        this.scheduledMailService.update(scheduledMailId, mailParamWrapper.getMail(), mailParamWrapper.getParam(),
                reportType, mailParamWrapper.getSchedule());
        return ResponseEntity.ok("Запланированная рассылка изменена.");
    }
}
