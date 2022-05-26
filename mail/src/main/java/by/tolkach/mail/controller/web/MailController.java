package by.tolkach.mail.controller.web;

import by.tolkach.mail.dto.MailParamWrapper;
import by.tolkach.mail.dto.ReportType;
import by.tolkach.mail.service.mail.api.IMailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mail")
public class MailController {

    private final IMailService mailService;

    public MailController(IMailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page") Integer page,
                                   @RequestParam(name = "size") Integer size) {
        return ResponseEntity.ok(this.mailService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(value = "/{type}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> send(@PathVariable(name = "type") ReportType reportType,
                                  @RequestBody MailParamWrapper mailParamWrapper) {
        this.mailService.create(mailParamWrapper.getMail(), mailParamWrapper.getParam(), reportType);
        return ResponseEntity.ok("Письмо с отчетом отправлено.");
    }
}
