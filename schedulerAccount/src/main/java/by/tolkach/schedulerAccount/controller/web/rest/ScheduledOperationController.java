package by.tolkach.schedulerAccount.controller.web.rest;

import by.tolkach.schedulerAccount.controller.web.PageChecker;
import by.tolkach.schedulerAccount.dto.scheduledOperation.ScheduleAndOperationWrapper;
import by.tolkach.schedulerAccount.service.scheduledOperation.api.IScheduledOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@RestController
@RequestMapping("/scheduler/operation")
public class ScheduledOperationController {

    private final IScheduledOperationService scheduledOperationService;

    public ScheduledOperationController(IScheduledOperationService scheduledOperationService) {
        this.scheduledOperationService = scheduledOperationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> index(@RequestParam(name = "page",required = false) Integer page,
                                   @RequestParam(name = "size", required = false) Integer size) {
        return ResponseEntity.ok(this.scheduledOperationService.read(PageChecker.checkParameters(page, size)));
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> create(@RequestBody ScheduleAndOperationWrapper wrapper) {
        this.scheduledOperationService.create(wrapper.getSchedule(), wrapper.getOperation());
        return ResponseEntity.ok("Операция добавлена к счету");
    }

    @RequestMapping(value = "/{uuid}/dt_update/{dt_update}",method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable(name = "uuid") UUID scheduledOperationId,
                                    @PathVariable(name = "dt_update") Long dtUpdate,
                                    @RequestBody ScheduleAndOperationWrapper wrapper) {
        this.scheduledOperationService.update(scheduledOperationId,
                LocalDateTime.ofEpochSecond(dtUpdate, 0, ZoneOffset.UTC),
                wrapper.getSchedule(), wrapper.getOperation());
        return ResponseEntity.ok("Запланированная операция изменена");
    }
}
