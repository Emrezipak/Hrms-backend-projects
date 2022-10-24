package KodlamaIo.hrms.controllers;

import java.util.List;

import KodlamaIo.hrms.business.abstracts.EmployerService;
import org.springframework.web.bind.annotation.*;
import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Employer;

import javax.validation.Valid;


@RestController
@RequestMapping("api/employer")
@CrossOrigin
public class EmployerController {

    private EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping("/getAllEmployer")
    public DataResult<List<Employer>> getAll() {
        return this.employerService.getAll();
    }

    @PostMapping("/addEmployer")
    public Result add(@RequestBody @Valid Employer employer) {
        return this.employerService.save(employer);
    }

    @DeleteMapping("/deleteEmployer")
    public Result deleteEmployer(@RequestParam String email) {
        return this.employerService.deleteEmployer(email);
    }

    @PutMapping("/updateEmployer")
    public DataResult<Employer> updateEmployer(@RequestParam Long id, @RequestBody Employer employer) {
        return this.employerService.updateEmployer(id, employer);
    }

    @GetMapping("/getEmployerByCompanyName")
    public DataResult<List<Employer>> updateEmployer(@RequestParam String companyName) {
        return this.employerService.getEmployerByCompanyName(companyName);
    }

}
