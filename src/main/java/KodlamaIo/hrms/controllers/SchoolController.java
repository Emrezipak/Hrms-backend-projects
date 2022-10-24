package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.SchoolService;
import KodlamaIo.hrms.payload.request.SchoolCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.School;

import javax.validation.Valid;

@RestController
@RequestMapping("api/school")
@CrossOrigin
public class SchoolController {

    private SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/addSchool")
    public Result save(@RequestBody @Valid SchoolCreateRequest school) {
        return this.schoolService.addSchoolInformation(school);
    }

    @GetMapping("/getByStartsSchoolYear")
    public Result getByStartsSchoolYear(@RequestParam Long id) {

        return this.schoolService.getByFinishSchoolYear(id);
    }

    @GetMapping("/getAllSchool")
    public Result getAllSchool() {
        return this.schoolService.getAllSchool();
    }

    @DeleteMapping("/deleteSchool")
    public Result deleteSchool(@RequestParam(name = "schoolId") Long schoolId) {
        return this.schoolService.deleteSchool(schoolId);
    }
}
