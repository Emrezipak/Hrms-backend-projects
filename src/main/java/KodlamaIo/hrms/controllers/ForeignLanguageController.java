package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.ForeignLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.ForeignLanguage;

@RestController
@RequestMapping("api/foreignlanguage")
@CrossOrigin
public class ForeignLanguageController {
	
	private ForeignLanguageService languageService;
	@Autowired
	public ForeignLanguageController(ForeignLanguageService languageService) {
		this.languageService = languageService;
	}
	
	@PostMapping("/addLanguage")
	public Result add(@RequestBody ForeignLanguage language) {
		return this.languageService.add(language);
	}
	
	@GetMapping("/getAllLanguage")
	public DataResult<List<ForeignLanguage>> getAll(){
		return this.languageService.getAllForeignLanguages();
	}
	

}
