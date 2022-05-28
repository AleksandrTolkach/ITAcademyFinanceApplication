package by.tolkach.mailScheduler.controller.web;

import by.tolkach.mailScheduler.dto.scheduledMail.MailParamWrapper;
import by.tolkach.mailScheduler.dto.scheduledMail.ReportType;
import by.tolkach.mailScheduler.service.scheduledMail.api.IScheduledMailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler/mail")
public class ScheduledMailController {

    private IScheduledMailService scheduledMailService;

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
    public ResponseEntity<?> create(@PathVariable(name = "type")ReportType reportType,
                                    @RequestBody MailParamWrapper mailParamWrapper) {
        this.scheduledMailService.create(mailParamWrapper.getMail(), mailParamWrapper.getParam(),
                reportType, mailParamWrapper.getSchedule());
    }
}
