package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.ExperienceService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.ExperienceDao;
import KodlamaIo.hrms.entity.concretes.Experience;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import KodlamaIo.hrms.dtos.ExperienceDto;
import KodlamaIo.hrms.payload.request.ExperienceCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExperienceManager implements ExperienceService {


	private CvIdControl cvIdControl;
	private ExperienceDao experienceDao;

	private ExperienceDto experienceDto;
	public ExperienceManager(ExperienceDao experienceDao,CvIdControl cvIdControl,ExperienceDto experienceDto) {
		this.experienceDao = experienceDao;
		this.cvIdControl = cvIdControl;
		this.experienceDto = experienceDto;
	}


	@Override
	public DataResult<Experience> add(ExperienceCreateRequest skill){
		DataResult<JobSeekerCv> jobSeekerCv = this.cvIdControl.getByJobSeekerCvId(skill.getJobSeekerCvId());
        try {
            Experience experience = requestToExperienceDto(skill,jobSeekerCv.getData());
            this.experienceDao.save(experience);
            return new SuccessDataResult(experience,"Successfully added experiences");
        }
        catch (Exception e){
            return new ErrorDataResult(jobSeekerCv.getMessage());
        }
	}

	@Override
	public DataResult<List<Experience>> getByFinishYearCv(Long id) {

		if(this.experienceDao.getByJobSeekerCv_CvIdOrderByFinishYear(id).stream().count()==0) {
			return new ErrorDataResult<>("Aday bulunamadı...");
		}

		return new SuccessDataResult<List<Experience>>
		(this.experienceDao.getByJobSeekerCv_CvIdOrderByFinishYear(id),"Deneyimler tarihe sıralandı...");
	}

	@Override
	public DataResult<List<Experience>> getByJobSeeker(long id) {
		return new SuccessDataResult<List<Experience>>(this.experienceDao.getByJobSeekerCv_CvId(id),"Deneyimler listelendi...");
	}

	@Override
	public DataResult<List<Experience>> getAllExperience() {
		return new SuccessDataResult<>(this.experienceDao.findAll());
	}

	@Override
	public Result deleteExperience(Long id) {
		Optional<Experience> experience = experienceDao.findById(id);
		if(experience.isPresent()){
			experienceDao.deleteById(experience.get().getExperienceId());
			return new SuccessResult("Successfully experience deleted");
		}
		return new ErrorResult("not found Information of experience");
	}

	private Experience requestToExperienceDto(ExperienceCreateRequest experienceRequest,
											  JobSeekerCv jobSeekerCv){
		return experienceDto.createExperienceObject(experienceRequest,jobSeekerCv);
	}

}
