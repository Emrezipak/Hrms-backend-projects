package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.SchoolService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.SchoolDao;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import KodlamaIo.hrms.entity.concretes.School;
import KodlamaIo.hrms.dtos.SchoolDto;
import KodlamaIo.hrms.payload.request.SchoolCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SchoolManager implements SchoolService {

    private SchoolDao schoolDao;
    private CvIdControl cvIdControl;

    private SchoolDto schoolDto;


    public SchoolManager(SchoolDao schoolDao, CvIdControl cvIdControl,SchoolDto schoolDto) {
        this.schoolDao = schoolDao;
        this.cvIdControl = cvIdControl;
        this.schoolDto = schoolDto;
    }

    @Override
    public DataResult<School> addSchoolInformation(SchoolCreateRequest newSchool) {
        try {
            DataResult<JobSeekerCv> jobSeekerCv = this.cvIdControl.getByJobSeekerCvId(newSchool.getJobSeekerCvId());
            School school = requestToSchoolDto(newSchool, jobSeekerCv.getData());
            this.schoolDao.save(school);
            return new SuccessDataResult(school, "Successfully added school information");
        } catch (Exception e) {
            return new ErrorDataResult(e.getMessage());
        }
    }

    @Override
    public DataResult<List<School>> getByFinishSchoolYear(Long id) {

        if (this.schoolDao.getByJobSeekerCv_CvIdOrderByFinishYearDesc(id).stream().count() == 0) {
            return new ErrorDataResult<>("Aday bulunamadı");
        }
        return new SuccessDataResult<List<School>>
                (this.schoolDao.getByJobSeekerCv_CvIdOrderByFinishYearDesc(id), "listed School information by date");
    }

    @Override
    public DataResult<List<School>> getAllSchool() {
        return new SuccessDataResult<List<School>>
                (this.schoolDao.findAll(), "listed schools");
    }

    @Override
    public DataResult<List<School>> getByJobSeeker(Long id) {
        return new SuccessDataResult<List<School>>(this.schoolDao.getByJobSeekerCv_CvId(id), "Listed schools information by id");
    }

    @Override
    public Result deleteSchool(Long id) {
        Optional<School> experience = schoolDao.findById(id);
        if (experience.isPresent()) {
            schoolDao.deleteById(experience.get().getSchoolId());
            return new SuccessResult("Successfully school deleted");
        }
        return new ErrorResult("not found Information of school");
    }

    private School requestToSchoolDto(SchoolCreateRequest schoolRequest, JobSeekerCv jobSeekerCv) {
        return schoolDto.createSchoolObject(schoolRequest, jobSeekerCv);
    }

}
