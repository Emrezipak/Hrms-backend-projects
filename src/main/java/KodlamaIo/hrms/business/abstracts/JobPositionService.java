package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.JobPosition;
import KodlamaIo.hrms.entity.concretes.JobSeeker;

import java.util.List;

public interface JobPositionService {

    DataResult<List<JobPosition>> getJobPositionsByJobNameIsLike(String jobName);
    DataResult<JobPosition> getJobPositionByJobName (String jobName);
    Result deleteJobPosition(Long id);
    Result addJobPosition(JobPosition jobPosition);
    DataResult<List<JobPosition>> getAllJobPosition();
    JobPosition findJobPositionByJobName(String email);


}
