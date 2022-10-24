package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.WorkTime;

import java.util.List;

public interface WorkTimeService{


    DataResult<WorkTime> addWork(WorkTime workTime);
    DataResult<List<WorkTime>> getAllWorkTime();

    Result deleteWorkTimeById(Integer id);
    DataResult<WorkTime> updateWork(Integer id,WorkTime workTime);

    DataResult<WorkTime> getWorkTimeById(Integer workTimeId);

}
