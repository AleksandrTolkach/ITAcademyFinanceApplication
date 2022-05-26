package by.tolkach.mail;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


public class TestController {

    private final TestMailService mailService;

    public TestController(TestMailService mailService) {
        this.mailService = mailService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> index() {
        this.mailService.sendEmailAttachment("shuuurick@mail.ru", "HHH", "Privet!",
                "/home/hoho/dev/aaa.xlsx");
        return ResponseEntity.ok().build();
    }
}
