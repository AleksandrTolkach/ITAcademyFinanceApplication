package by.tolkach.account.controller.web.rest;

import by.tolkach.account.dto.Account;
import by.tolkach.account.dto.SimplePageable;
import by.tolkach.account.service.api.IAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page", required = false) Integer page,
                                               @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.accountService.read(new SimplePageable(page, size)));
    }

    @RequestMapping(value = {"/", "/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@PathVariable UUID id) {
        return ResponseEntity.ok(this.accountService.read(id));
    }

    @RequestMapping(value = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable UUID id,
                                    @PathVariable(name = "dt_update") Long dtUpdate,
                                    @RequestBody Account account) {
        this.accountService.update(id, LocalDateTime.ofEpochSecond(dtUpdate, 0, ZoneOffset.UTC), account);
        return ResponseEntity.ok("Счёт обновлен");
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody Account account) {
        this.accountService.create(account);
        return ResponseEntity.ok("Счёт добавлен в ваш профиль");
    }
}
