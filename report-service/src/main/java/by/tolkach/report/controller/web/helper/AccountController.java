package by.tolkach.report.controller.web.helper;

import by.tolkach.report.dto.Account;
import by.tolkach.report.service.helper.api.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/report/account")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Account account) {
        this.accountService.save(account);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Account account) {
        this.accountService.update(account);
        return ResponseEntity.ok().build();
    }
}
