package by.tolkach.schedulerAccount.controller.web.rest;

import by.tolkach.schedulerAccount.controller.advice.WrapperValidation;
import by.tolkach.schedulerAccount.controller.web.PageChecker;
import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduleAndOperationWrapper;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduledOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
@RequestMapping("/scheduler/operation")
public class ScheduledOperationController {

    private final IScheduledOperationService scheduledOperationService;
    private final WrapperValidation wrapperValidation;

    public ScheduledOperationController(IScheduledOperationService scheduledOperationService,
                                        WrapperValidation wrapperValidation) {
        this.scheduledOperationService = scheduledOperationService;
        this.wrapperValidation = wrapperValidation;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page",required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.scheduledOperationService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody(required = false) ScheduleAndOperationWrapper wrapper) {
        this.wrapperValidation.validate(wrapper);
        this.scheduledOperationService.create(wrapper.getSchedule(), wrapper.getOperation());
        return ResponseEntity.ok("Операция запланирована");
    }

    @RequestMapping(value = "/{uuid}/dt_update/{dt_update}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(name = "uuid") UUID scheduledOperationId,
                                    @PathVariable(name = "dt_update") Long dtUpdate,
                                    @RequestBody(required = false) ScheduleAndOperationWrapper wrapper) {
        this.wrapperValidation.validate(wrapper);
        this.scheduledOperationService.update(scheduledOperationId,
                LocalDateTime.ofInstant(Instant.ofEpochMilli(dtUpdate), ZoneOffset.of("+03:00")),
                wrapper.getSchedule(), wrapper.getOperation());
        return ResponseEntity.ok("Запланированная операция изменена");
    }
}
