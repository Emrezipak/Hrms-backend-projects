package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Technology;

import javax.validation.Valid;

@RestController
@RequestMapping("api/technology")
@CrossOrigin
public class TechnologyController {

    private TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }


    @PostMapping("/addNewSkill")
    public Result add(@RequestBody @Valid Technology skill) {
        return this.technologyService.add(skill);
    }


    @GetMapping("/getAllSkills")
    public Result getAll() {
        return this.technologyService.getAllTechnologies();
    }


}
