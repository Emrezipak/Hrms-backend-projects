package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.JobSeekerService;
import KodlamaIo.hrms.business.abstracts.UserService;
import KodlamaIo.hrms.core.mernis.abstracts.MernisService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.JobSeekerDao;
import KodlamaIo.hrms.entity.concretes.JobSeeker;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobSeekerManager implements JobSeekerService {

	private JobSeekerDao jobSeekerDao;
	private UserService userService;
	private MernisService mernisService;
	public JobSeekerManager(JobSeekerDao jobSeekerDao, UserService userService, MernisService mernisService) {
		this.jobSeekerDao = jobSeekerDao;
		this.userService=userService;
		this.mernisService=mernisService;
	}

	@Override
	public DataResult<List<JobSeeker>> getAllJobSeeker() {
		return new SuccessDataResult<List<JobSeeker>>(jobSeekerDao.findAll(),"listed job seeker");
	}

	@Override
	public DataResult<JobSeeker> save(JobSeeker user) {

		if(controlEmailAndIdentificationNo(user)) {
			return new ErrorDataResult("previously registered person");
		}

		if(checkMernisService(user)){
			return new ErrorDataResult("not match your information");
		}
		this.jobSeekerDao.save(user);
		this.userService.sendMesagge(user.getEmail());
		return new SuccessDataResult("added JobSeeker");
	}

	private boolean controlEmailAndIdentificationNo(JobSeeker jobSeeker) {
		if(getJobSeekerByEmail(jobSeeker.getEmail()).isSuccess()==true ||
				getJobSeekerByIdentificationNo(jobSeeker.getIdentificationNo()).isSuccess()) {
			return true;
		}
		return false;
	}

	@Override
	public DataResult<JobSeeker> getById(long id) {
		return new SuccessDataResult<JobSeeker>(jobSeekerDao.getById(id));
	}

	@Override
	public DataResult<JobSeeker> getJobSeekerByEmail(String email) {
		Optional<JobSeeker> jobSeeker = this.jobSeekerDao.getJobSeekerByEmail(email);
		if(jobSeeker.isPresent()){
			return new SuccessDataResult(jobSeeker.get(),"Success");
		}
		return new ErrorDataResult("not found job seeker");
	}

	@Override
	public DataResult<JobSeeker> getJobSeekerByIdentificationNo(String idNo) {
		Optional<JobSeeker> jobSeeker = this.jobSeekerDao.getJobSeekerByidentificationNo(idNo);
		if(jobSeeker.isPresent()){
			return new SuccessDataResult(jobSeeker.get(),"Success");
		}
		return new ErrorDataResult("not found job seeker ");

	}

	@Override
	public Result deleteJobSeekerByEmail(String email) {
		Optional<JobSeeker> jobSeeker = this.jobSeekerDao.getJobSeekerByEmail(email);
		if(jobSeeker.isPresent()){
			this.jobSeekerDao.deleteById(jobSeeker.get().getId());
			return new SuccessResult("deleted job seeker");
		}
		return new ErrorResult("not found job seeker");
	}

	@Override
	public JobSeeker getDataOfJobSeekerByEmail(String email) {
		Optional<JobSeeker> jobSeeker = this.jobSeekerDao.getJobSeekerByEmail(email);
		if(jobSeeker.isPresent()){
			return jobSeeker.get();
		}
		return null;
	}

	private boolean checkMernisService(JobSeeker jobSeeker){
		return !mernisService.checkMernisService(jobSeeker);
	}

}
