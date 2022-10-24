package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Experience;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import KodlamaIo.hrms.payload.request.ExperienceCreateRequest;

import java.util.List;

public interface ExperienceService{

    DataResult<Experience> add(ExperienceCreateRequest skill);
    //Result add(ExperienceCreateRequest skill,long id);

    DataResult<List<Experience>> getByFinishYearCv(Long id);
	DataResult<List<Experience>> getByJobSeeker(long id);

    DataResult<List<Experience>> getAllExperience();

    Result deleteExperience(Long id);
}
