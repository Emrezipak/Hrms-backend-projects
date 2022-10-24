package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.WorkType;

import java.util.List;

public interface WorkTypeService{

    DataResult<WorkType> addWorkType(WorkType workType);
    DataResult<WorkType> getWorkTypeById(Integer id);
    DataResult<List<WorkType>> getAllWorkType();
    Result deleteWorkTypeById(Integer id);
    DataResult<WorkType> updateWorkType(Integer id,WorkType workType);




}
