package by.tolkach.schedulerAccount.controller;

import by.tolkach.schedulerAccount.service.SchedulerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@RequestMapping("/test/scheduler")
public class SchedulerTest {

    private final SchedulerService schedulerService;

    public SchedulerTest(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public void create() {
        this.schedulerService.create(UUID.randomUUID());
    }
}
