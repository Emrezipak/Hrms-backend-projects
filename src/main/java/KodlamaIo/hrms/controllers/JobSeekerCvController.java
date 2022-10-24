package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.JobSeekerCvService;
import KodlamaIo.hrms.payload.request.CvBaseRequest;
import KodlamaIo.hrms.payload.request.CvRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("api/jobSeekerCv")
@CrossOrigin
public class JobSeekerCvController {
	
	private JobSeekerCvService jobSeekerCvService;
	
	public JobSeekerCvController(JobSeekerCvService jobSeekerCvService) {
		this.jobSeekerCvService = jobSeekerCvService;
	}	
	
	@GetMapping("/getAllCv")
	public DataResult<List<JobSeekerCv>> getAllCv(){
		return this.jobSeekerCvService.getAllCvs();
	}
	
	@PostMapping("/createBaseCv")
	public Result add(@RequestBody @Valid CvBaseRequest cvRequest) {
		return this.jobSeekerCvService.createCv(cvRequest);
	}

	@PutMapping(value = "/updateCv")
	public DataResult<JobSeekerCv> updateCv(@RequestBody @Valid CvRequest cvRequest,
											@RequestParam(name = "cvId")Long cvId) {
		return this.jobSeekerCvService.updateCv(cvRequest,cvId);
	}
	
	@GetMapping("/getByIdAndIdentificationNo")
	public DataResult<List<JobSeekerCv>> getByIdCv(@RequestParam long id,@RequestParam String identificationNo) {
		return this.jobSeekerCvService.getByIdCv(id, identificationNo);
	}


	@GetMapping("/getByJobSeekerCvId/{id}")
	public JobSeekerCv getByJobSeekerCvId(@PathVariable long id){
		return this.jobSeekerCvService.getByJobSeekerCvId(id);
	}

	@GetMapping("/getAllCvByUserId")
	public DataResult<List<JobSeekerCv>> getAllCvByUserId(@RequestParam(name = "userId") Long id){
		return this.jobSeekerCvService.getAllCvByUserId(id);
	}
	
}
