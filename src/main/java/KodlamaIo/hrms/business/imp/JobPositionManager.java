package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.JobPositionService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.JobPositionDao;
import KodlamaIo.hrms.entity.concretes.JobPosition;
import KodlamaIo.hrms.entity.concretes.JobSeeker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobPositionManager implements JobPositionService {

	private JobPositionDao jobPositionDao;

	public JobPositionManager(JobPositionDao jobPositionDao) {
		this.jobPositionDao = jobPositionDao;
	}

	@Override
	public DataResult<List<JobPosition>> getAllJobPosition() {
		return new SuccessDataResult<List<JobPosition>>(jobPositionDao.findAll(),"job positions listed");
	}

	@Override
	public Result addJobPosition(JobPosition user) {
		user.setJobName(user.getJobName().toLowerCase());
		if(user.getJobName()==null || user.getJobName().trim().equals("")) {
			return new ErrorResult("job name not be null");
		}
		else if(getJobPositionByJobName(user.getJobName()).isSuccess()) {
			return new ErrorResult("this job position is exist");
		}
		
		this.jobPositionDao.save(user);
		return new SuccessResult("added job position");
	}


	@Override
	public DataResult<List<JobPosition>> getJobPositionsByJobNameIsLike(String jobName) {
		return new SuccessDataResult(
				this.jobPositionDao.getJobPositionsByJobName(jobName));
	}

	@Override
	public DataResult<JobPosition> getJobPositionByJobName(String jobName) {
		Optional<JobPosition> jobPosition=this.jobPositionDao.getJobPositionByJobName(jobName);
		if(jobPosition.isPresent()){
			return new SuccessDataResult(jobPosition.get(),"job position found");
		}
		return new ErrorDataResult("not found job position");
	}

	@Override
	public Result deleteJobPosition(Long id) {
		Optional<JobPosition> jobPosition=this.jobPositionDao.findById(id);
		if(jobPosition.isPresent()){
			this.jobPositionDao.deleteById(jobPosition.get().getId());
			return new SuccessResult("delete job position");
		}
		return new ErrorResult("not found job position");
	}

	@Override
	public JobPosition findJobPositionByJobName(String email) {
		Optional<JobPosition> jobPosition=this.jobPositionDao.getJobPositionByJobName(email);
		if(jobPosition.isPresent()){
			return jobPosition.get();
		}
		return null;
	}

}
