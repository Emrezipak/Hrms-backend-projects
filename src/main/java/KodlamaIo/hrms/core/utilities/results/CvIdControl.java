package KodlamaIo.hrms.core.utilities.results;

import KodlamaIo.hrms.dataAccess.JobSeekerCvDao;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CvIdControl {

    private JobSeekerCvDao jobSeekerCvDao;

    public CvIdControl(JobSeekerCvDao jobSeekerCvDao) {
        this.jobSeekerCvDao = jobSeekerCvDao;
    }

    public DataResult<JobSeekerCv> getByJobSeekerCvId(Long id) {
        Optional<JobSeekerCv> jobSeekerCv = this.jobSeekerCvDao.findById(id);
        if (jobSeekerCv.isPresent()) {
            return new SuccessDataResult<>(jobSeekerCv.get(), "Successfully Cv retrieved");
        }

        return new ErrorDataResult<>("not found cv");
    }
}
