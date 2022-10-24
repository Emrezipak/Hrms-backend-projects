package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.JobSeekerService;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import org.springframework.web.bind.annotation.*;

import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.entity.concretes.JobSeeker;

import javax.validation.Valid;


@RestController
@RequestMapping("api/jobSeeker")
@CrossOrigin
public class JobSeekerController {

	private JobSeekerService jobSeekerService;

	public JobSeekerController(JobSeekerService jobSeekerService) {
		this.jobSeekerService = jobSeekerService;
	}
    
    @GetMapping("/getAllJobSeekers")
    public DataResult<List<JobSeeker>> getAll(){
    	return this.jobSeekerService.getAllJobSeeker();
    }

    @PostMapping("/addJobSeeker")
    public Result add(@RequestBody @Valid JobSeeker jobSeeker) {
    	return this.jobSeekerService.save(jobSeeker);
    }
    @GetMapping("/getJobSeekerByEmail")
    public DataResult<JobSeeker> getJobSeekerByEmail(@RequestParam String email){
        return this.jobSeekerService.getJobSeekerByEmail(email);
    }
    @GetMapping("/getJobSeekerByIdentificationNo")
    public DataResult<JobSeeker> getJobSeekerByIdentificationNo(@RequestParam String IdentificationNo){
        return this.jobSeekerService.getJobSeekerByIdentificationNo(IdentificationNo);
    }
    @DeleteMapping("/deleteJobSeekerByEmail")
    public Result deleteJobSeekerByEmail(@RequestParam String email){
        return this.jobSeekerService.deleteJobSeekerByEmail(email);
    }

}
