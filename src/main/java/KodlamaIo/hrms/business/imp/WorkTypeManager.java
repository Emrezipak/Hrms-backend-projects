package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.WorkTypeService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.WorkTypeDao;
import KodlamaIo.hrms.entity.concretes.WorkType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkTypeManager implements WorkTypeService {

    private WorkTypeDao workTypeDao;

    public WorkTypeManager(WorkTypeDao workTypeDao) {
        this.workTypeDao = workTypeDao;
    }

    @Override
    public DataResult<WorkType> addWorkType(WorkType workType) {
        return new SuccessDataResult(this.workTypeDao.save(workType),"Successfully added work type");
    }

    @Override
    public DataResult<List<WorkType>> getAllWorkType() {
        return new SuccessDataResult(this.workTypeDao.findAll(),"Listed work types");
    }

    @Override
    public Result deleteWorkTypeById(Integer id) {
        Optional<WorkType> workType=this.workTypeDao.findById(id);

        if(workType.isPresent()){
            this.workTypeDao.deleteById(id);
            return new SuccessResult("deleted work type");
        }
        return new ErrorResult("not found work type");

    }

    @Override
    public DataResult<WorkType> updateWorkType(Integer id, WorkType newWorkType) {
        Optional<WorkType> workType=this.workTypeDao.findById(id);
        if(workType.isPresent()){
            workType.get().setTypeName(newWorkType.getTypeName());
            return new SuccessDataResult(this.workTypeDao.save(workType.get()),"Success");
        }
        return new ErrorDataResult("not found work type");

    }

    private boolean controlWorkTypeName(String name){
        if(this.workTypeDao.getByTypeName(name)!=null){
            return true;
        }
        return false;
    }

    @Override
    public DataResult<WorkType> getWorkTypeById(Integer id) {
        Optional<WorkType> workType=this.workTypeDao.findById(id);
        if(workType.isPresent()){
            return new SuccessDataResult(workType.get(),"Success");
        }
        return new ErrorDataResult("not found work type");
    }
}
