package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.*;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.JobSeekerCvDao;
import KodlamaIo.hrms.entity.concretes.*;
import KodlamaIo.hrms.dtos.BaseCvDto;
import KodlamaIo.hrms.payload.request.CvBaseRequest;
import KodlamaIo.hrms.payload.request.CvRequest;
import KodlamaIo.hrms.payload.request.ExperienceCreateRequest;
import KodlamaIo.hrms.payload.request.SchoolCreateRequest;
import KodlamaIo.hrms.payload.response.CvBaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobSeekerCvManager implements JobSeekerCvService {

    private final JobSeekerCvDao jobSeekerCvDao;
    private final JobSeekerService jobSeekerService;
    private final ExperienceService experienceService;
    private final ForeignLanguageService foreignLanguageService;
    private final SchoolService schoolService;
    private final TechnologyService technologyService;
    private final ImageService imageService;
    private final BaseCvDto baseCvDto;


    @Override
    public DataResult<List<JobSeekerCv>> getAllCvs() {
        return new SuccessDataResult(this.jobSeekerCvDao.findAll(), "Listed Cvs");
    }

    @Override
    public DataResult<CvBaseResponse> createCv(CvBaseRequest cvBaseRequest) {
        DataResult<JobSeeker> jobSeeker = this.jobSeekerService.getJobSeekerByEmail(cvBaseRequest.getJobSeekerEmail());
        if (!jobSeeker.isSuccess()) {
            return new ErrorDataResult<>("not found user");
        }

        JobSeekerCv jobSeekerCv = convertDtoObjectForCvRequest(cvBaseRequest, jobSeeker.getData());

        JobSeekerCv jobSeekerCvStore = this.jobSeekerCvDao.save(jobSeekerCv);
        jobSeekerCvDao.flush();
        return new SuccessDataResult<>(CvBaseResponse.builder().cvId(jobSeekerCvStore.getCvId())
                .email(jobSeekerCvStore.getJobSeeker().getEmail())
                .userId(jobSeekerCvStore.getJobSeeker().getId()).build()
                , "created new cv for user");
    }

    @Override
    public DataResult<JobSeekerCv> updateCv(CvRequest cvRequest, Long id) {
        Optional<JobSeekerCv> jobSeekerCv = jobSeekerCvDao.getJobSeekerCvByCvId(id);
        if (jobSeekerCv.isPresent()) {
            DataResult<Experience> experienceDataResult = addExperienceToCv(cvRequest.getExperience());
            DataResult<School> schoolDataResult = addSchoolInformToCv(cvRequest.getSchools());
            if (!experienceDataResult.isSuccess()) {
                return new ErrorDataResult(experienceDataResult.getMessage());
            } else if (!schoolDataResult.isSuccess()) {
                return new ErrorDataResult(schoolDataResult.getMessage());
            }
            JobSeekerCv jobSeekerCvUpdated = createDtoJobSeekerCv(cvRequest, jobSeekerCv.get());
            jobSeekerCvDao.save(jobSeekerCvUpdated);
            jobSeekerCvDao.flush();
            return new SuccessDataResult(jobSeekerCv.get(), "Successfully Cv Updated");
        }
        return new ErrorDataResult("not found cv");
    }

    private JobSeekerCv createDtoJobSeekerCv(CvRequest cvRequest, JobSeekerCv jobSeekerCv) {
        return JobSeekerCv.builder().cvId(jobSeekerCv.getCvId())
                .jobSeeker(jobSeekerCv.getJobSeeker())
                .explanation(jobSeekerCv.getExplanation())
                .githubAddress(cvRequest.getGithubAddress())
                .linkedlnAddress(cvRequest.getLinkedLnAddress())
                .technologies(stringToTechnologiesList(cvRequest.getTechnologies()))
                .languages(stringToLanguageList(cvRequest.getForeignLanguages()))
                .build();
    }

    private DataResult<Experience> addExperienceToCv(Set<ExperienceCreateRequest> experiencesRequest) {
        DataResult<Experience> experiences = null;
        for (ExperienceCreateRequest experience : experiencesRequest) {
            experiences = experienceService.add(experience);
            if (!experiences.isSuccess()) {
                return new ErrorDataResult(experiences.getMessage());
            }
        }
        return new SuccessDataResult(experiences, "Success");
    }

    private DataResult<School> addSchoolInformToCv(Set<SchoolCreateRequest> experiencesRequest) {
        DataResult<School> schoolList = null;
        for (SchoolCreateRequest schoolInform : experiencesRequest) {
            schoolList = schoolService.addSchoolInformation(schoolInform);
            if (!schoolList.isSuccess()) {
                return new ErrorDataResult(schoolList.getMessage());
            }
        }
        return new SuccessDataResult(schoolList, "Success");
    }

    private Set<Technology> stringToTechnologiesList(Set<String> technologies) {
        return technologies.stream().map((technology) ->
                        technologyService.getTechnologiesByTechnologyName(technology).getData())
                .collect(Collectors.toSet());

    }

    private Set<ForeignLanguage> stringToLanguageList(Set<ForeignLanguage> languages) {
        return languages.stream().map((language) ->
                foreignLanguageService.getForeignLanguageByLanguageName(language).getData()
        ).collect(Collectors.toSet());

    }

    private Image saveImage(Long cvId, MultipartFile multipartFile) {
        DataResult<Image> image = imageService.addImage(cvId, multipartFile);
        return image.getData();
    }

    @Override
    public DataResult<List<JobSeekerCv>> getByIdCv(long id, String identificationNo) {

        if (!controlIdentificationNo(identificationNo)) {
            return new ErrorDataResult<>("Bu numarada bir iş arayan mevcut değil");
        } else if (id <= 0 || identificationNo.trim().equals("")) {
            return new ErrorDataResult<>("Gerekli alanları doldurun");
        }
        return new SuccessDataResult<List<JobSeekerCv>>
                (this.jobSeekerCvDao.getByCvIdOrJobSeeker_identificationNo(id, identificationNo), "Cv'ler listelendi");
    }

    private boolean controlIdentificationNo(String identificationNo) {

        if (jobSeekerService.getJobSeekerByIdentificationNo(identificationNo).isSuccess()) {
            return false;
        }
        return true;
    }

    public JobSeekerCv getByJobSeekerCvId(long id) {
        Optional<JobSeekerCv> jobSeekerCv = this.jobSeekerCvDao.findById(id);
        if (jobSeekerCv.isPresent()) {
            return jobSeekerCv.get();
        } else {
            return jobSeekerCv.orElse(null);
        }
    }

    @Override
    public DataResult<List<JobSeekerCv>> getAllCvByUserId(Long jobSeekerId) {
        if (getByJobSeekerCvId(jobSeekerId) != null) {
            List<JobSeekerCv> jobSeekerCvs = this.jobSeekerCvDao.getAllByJobSeeker_Id(jobSeekerId);
            return new SuccessDataResult(jobSeekerCvs, "listed Cvs of user");
        }
        return new ErrorDataResult("not found user");
    }

    private JobSeekerCv convertDtoObjectForCvRequest(CvBaseRequest cvRequest, JobSeeker jobSeeker) {
        return baseCvDto.getJobSeekerCv(cvRequest, jobSeeker);
    }
}
