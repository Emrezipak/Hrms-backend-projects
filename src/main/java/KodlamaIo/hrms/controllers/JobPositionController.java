package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.JobPositionService;
import org.springframework.web.bind.annotation.*;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.JobPosition;


@RestController
@RequestMapping("api/jobPosition")
@CrossOrigin
public class JobPositionController {

    private JobPositionService jobPositionService;

    public JobPositionController(JobPositionService jobPositionService) {
        this.jobPositionService = jobPositionService;
    }

    @GetMapping("/getAllJobPosition")
    public Result getAllJobPosition() {
        return jobPositionService.getAllJobPosition();
    }

    @PostMapping("/addJobPosition")
    public Result addJobPosition(@RequestBody JobPosition jobPosition) {
        return this.jobPositionService.addJobPosition(jobPosition);
    }

    @DeleteMapping("/deleteJobPosition/{id}")
    public Result deleteJobPosition(@PathVariable Long id) {
        return this.jobPositionService.deleteJobPosition(id);
    }

    @GetMapping("/getJobPositionByJobName")
    public Result getJobPositionByJobName(@RequestParam String jobName) {
        return this.jobPositionService.getJobPositionsByJobNameIsLike(jobName);
    }

}
