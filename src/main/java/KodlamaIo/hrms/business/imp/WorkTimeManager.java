package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.WorkTimeService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.WorkTimeDao;
import KodlamaIo.hrms.entity.concretes.WorkTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkTimeManager implements WorkTimeService {

    private WorkTimeDao workTimeDao;

    public WorkTimeManager(WorkTimeDao workTimeDao) {
        this.workTimeDao = workTimeDao;
    }

    @Override
    public DataResult<WorkTime> addWork(WorkTime workTime) {
        return new SuccessDataResult(this.workTimeDao.save(workTime),"success");
    }

    @Override
    public DataResult<List<WorkTime>> getAllWorkTime() {
        return new SuccessDataResult(this.workTimeDao.findAll(),"listed work time");
    }

    @Override
    public Result deleteWorkTimeById(Integer id) {
        Optional<WorkTime> workType=this.workTimeDao.findById(id);
        if(workType.isPresent()){
            this.workTimeDao.deleteById(id);
            return new SuccessResult("successfully deleted work time");
        }
        return new ErrorResult("not found work time");
    }

    @Override
    public DataResult<WorkTime> updateWork(Integer id, WorkTime newWorkTime) {
        Optional<WorkTime> workTime=this.workTimeDao.findById(id);
        if(workTime.isPresent()){
            workTime.get().setTimeName(newWorkTime.getTimeName());
            return new SuccessDataResult(this.workTimeDao.save(workTime.get()),"success");
        }
        return new ErrorDataResult("not found work time");

    }

    private boolean controlWorkTypeName(String name){
        if(this.workTimeDao.getByTimeName(name)!=null){
            return true;
        }
        return false;
    }

    @Override
    public DataResult<WorkTime> getWorkTimeById(Integer workTimeId) {
        Optional<WorkTime> workTime=this.workTimeDao.findById(workTimeId);
        if(workTime.isPresent()){
            return new SuccessDataResult(workTime.get(),"Success");
        }
        return new ErrorDataResult("not found work time");
    }
}
