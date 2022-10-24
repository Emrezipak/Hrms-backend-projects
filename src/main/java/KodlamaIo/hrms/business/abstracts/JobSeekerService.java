package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Employer;
import KodlamaIo.hrms.entity.concretes.JobSeeker;

import java.util.List;

public interface JobSeekerService {

	DataResult<JobSeeker> getById(long id);

	DataResult<JobSeeker> getJobSeekerByEmail(String email);

	DataResult<JobSeeker> getJobSeekerByIdentificationNo(String idNo);

	Result deleteJobSeekerByEmail(String email);

	JobSeeker getDataOfJobSeekerByEmail(String email);

	DataResult<List<JobSeeker>> getAllJobSeeker();
	DataResult<JobSeeker> save(JobSeeker jobSeeker);
}
