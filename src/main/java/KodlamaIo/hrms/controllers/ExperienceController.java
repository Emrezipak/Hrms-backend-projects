package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.ExperienceService;
import KodlamaIo.hrms.payload.request.ExperienceCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Experience;

import javax.validation.Valid;

@RestController
@RequestMapping("api/experience")
@CrossOrigin
public class ExperienceController {

	private ExperienceService experienceService;

	public ExperienceController(ExperienceService experienceService) {
		this.experienceService = experienceService;
	}
	
	@PostMapping("/addExperience")
	public Result save(@RequestBody @Valid ExperienceCreateRequest skill) {
		return this.experienceService.add(skill);
	}
	
	
	@GetMapping("/getByStartsExperienceYear")
	public DataResult<List<Experience>> getByFinishYearCv(@RequestParam Long id) {
		return this.experienceService.getByFinishYearCv(id);
	}

	@GetMapping("/getAllExperience")
	public DataResult<List<Experience>> getAllExperience(){
		return this.experienceService.getAllExperience();
	}

	@DeleteMapping("/deleteExperienceById")
	public Result deleteExperience(@RequestParam(name = "experienceId") Long experienceId){
		return experienceService.deleteExperience(experienceId);
	}

}
