package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.WorkTimeService;
import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/workTime")
@CrossOrigin
public class WorkTimeController {


    private WorkTimeService workTimeService;

    public WorkTimeController(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }

    @PostMapping("/addWorkTime")
    public Result addWorkTime(@RequestBody @Valid WorkTime workTime) {
        return this.workTimeService.addWork(workTime);
    }

    @GetMapping("/getAllWorkTime")
    public Result getAllWorkTime() {
        return this.workTimeService.getAllWorkTime();
    }

    @DeleteMapping("/deleteWorkTime/{id}")
    public void deleteWorkType(@PathVariable int id) {
        this.workTimeService.deleteWorkTimeById(id);
    }

    @PutMapping("/updateWorkTime/{id}")
    public Result updateWorkTime(@PathVariable int id, @RequestBody @Valid WorkTime workTime) {
        return this.workTimeService.updateWork(id, workTime);
    }
}
