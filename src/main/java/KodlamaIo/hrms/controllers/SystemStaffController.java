package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.SystemStaffService;
import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.SystemStaff;
import KodlamaIo.hrms.payload.request.SystemStaffUpdateRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/systemStaff")
@CrossOrigin
public class SystemStaffController {

    private SystemStaffService systemStaffService;

    public SystemStaffController(SystemStaffService systemStaffService) {
        this.systemStaffService = systemStaffService;
    }

    @PostMapping("/addSystemStaff")
    public DataResult<SystemStaff> save(@RequestBody @Valid SystemStaff systemStaff) {
        return systemStaffService.addSystemStaff(systemStaff);
    }

    @PutMapping("/updateSystemStaff")
    public DataResult<SystemStaff> updateSystemStaff(@RequestBody @Valid SystemStaffUpdateRequest systemStaff,
                                                     @RequestParam(name = "email") String email) {
        return systemStaffService.updateSystemStaffByEmail(email, systemStaff);
    }

    @PutMapping("/changeStatusOfAdvert")
    public Result changeStatusOfAdvert(@RequestParam(name="id") Long id) {
        return systemStaffService.approvalToAdverts(id);
    }
}
