package by.tolkach.mail.controller.web;

import by.tolkach.mail.dto.MailParamWrapper;
import by.tolkach.mail.dto.ReportType;
import by.tolkach.mail.service.mail.api.IMailService;
import by.tolkach.mail.service.mail.api.IPostmanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final IPostmanService postmanService;

    public MailController(IPostmanService postmanService) {
        this.postmanService = postmanService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page") Integer page,
                                   @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(this.postmanService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> send(@PathVariable(name = "type") ReportType reportType,
                                  @RequestBody MailParamWrapper mailParamWrapper) {
        this.postmanService.send(mailParamWrapper.getMail(), mailParamWrapper.getParam(), reportType);
        return ResponseEntity.ok("Письмо с отчетом отправлено.");
    }

    @RequestMapping(value = "/{mail_id}/report/{report_id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> resend(@PathVariable(name = "mail_id") UUID mailId,
                                    @PathVariable(name = "report_id") UUID reportId) {
        this.postmanService.resend(mailId, reportId);
        return ResponseEntity.ok("Письмо с отчетом отправлено.");
    }
}
