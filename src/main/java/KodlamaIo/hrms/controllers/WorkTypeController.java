package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.WorkTypeService;
import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.entity.concretes.WorkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/workType")
@CrossOrigin
public class WorkTypeController {
    
    private WorkTypeService workTypeService;

    public WorkTypeController(WorkTypeService workTypeService) {
        this.workTypeService = workTypeService;
    }

    @PostMapping("/addWorkType")
    public DataResult<WorkType> addWorkType(@RequestBody @Valid WorkType workTime){
        return this.workTypeService.addWorkType(workTime);
    }
    @GetMapping("/getAllWorkType")
    public DataResult<List<WorkType>> getAllWorkType(){
        return this.workTypeService.getAllWorkType();
    }
    @DeleteMapping("/deleteWorkType/{id}")
    public void deleteWorkType(@PathVariable Integer id){
        this.workTypeService.deleteWorkTypeById(id);
    }
    @PutMapping("/updateWorkType/{id}")
    public DataResult<WorkType> updateWorkType(@PathVariable Integer id, @RequestBody @Valid WorkType workTime){
        return this.workTypeService.updateWorkType(id,workTime);
    }
}
