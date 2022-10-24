package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.*;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.JobAdvertisementDao;
import KodlamaIo.hrms.entity.concretes.*;
import KodlamaIo.hrms.dtos.AdvertDto;
import KodlamaIo.hrms.payload.request.JobAdvertisementRequest;
import KodlamaIo.hrms.payload.response.AdvertResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JobAdvertisementManager implements JobAdvertisementService {

    private JobAdvertisementDao jobAdvertisementDao;
    private CityService cityService;
    private EmployerService employerService;
    private JobPositionService jobPositionService;

    private WorkTimeManager workTimeManager;

    private WorkTypeManager workTypeManager;

    private AdvertDto advertDto;

    public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao,
                                   CityService cityService,
                                   EmployerService employerService,
                                   JobPositionService jobPositionService,
                                   WorkTimeManager workTimeManager,
                                   WorkTypeManager workTypeManager,
                                   AdvertDto advertDto) {
        this.jobAdvertisementDao = jobAdvertisementDao;
        this.cityService = cityService;
        this.employerService = employerService;
        this.jobPositionService = jobPositionService;
        this.workTimeManager = workTimeManager;
        this.workTypeManager = workTypeManager;
        this.advertDto = advertDto;
    }

    @Override
    public DataResult<JobAdvertisement> createAdvertisement(JobAdvertisementRequest jobAdvertisementRequest) {
        try {

            //get Employer from employer service
            DataResult<Employer> employer = getEmployerFromService(jobAdvertisementRequest.getCompanyEmail());

            //get job position from service
            DataResult<JobPosition> jobPosition = getJobPositionFromService(jobAdvertisementRequest.getJobName());

            //String map to city object
            Set<City> cities = findCitiesForAdvert(jobAdvertisementRequest.getCities());

            //get work time from service
            DataResult<WorkTime> workTime = getWorkTimeFromService(jobAdvertisementRequest.getWorkTimeId());

            //get work type from service
            DataResult<WorkType> workType = getWorkTypeFromService(jobAdvertisementRequest.getWorkTypeId());


            if (!employer.isSuccess()) {
                return new ErrorDataResult("not found company");
            } else if (!jobPosition.isSuccess()) {
                return new ErrorDataResult("not found job position");
            } else if (!workType.isSuccess()) {
                return new ErrorDataResult<>("not found work type");
            } else if (!workTime.isSuccess()) {
                return new ErrorDataResult("not found work time");
            }

            JobAdvertisement jobAdvertisement = convertDtoObjectForAdvert(jobAdvertisementRequest, employer.getData(), cities, jobPosition.getData(), workTime.getData(), workType.getData());

            this.jobAdvertisementDao.save(jobAdvertisement);
            return new SuccessDataResult(createAdvertResponse(jobAdvertisement), "added job advert");
        } catch (Exception e) {
            return new ErrorDataResult(e.getMessage());
        }

    }

    @Override
    public DataResult<Page<AdvertResponse>> getJobAdvertsByActive(Pageable pageable) {
        return new SuccessDataResult(jobAdvertisementDao.getByJobConditionIsTrue(pageable).map((advert) ->
                createAdvertResponse(advert)), "active job adverts listed");
    }

    @Override
    public DataResult<Page<AdvertResponse>> getByJobConditionTrueOrderByReleaseDateDesc(Pageable pageable) {
        return new SuccessDataResult(jobAdvertisementDao.getByJobConditionIsTrueOrderByReleaseDateDesc(pageable).map((advert) ->
                createAdvertResponse(advert)), "Job adverts listed by date");
    }

    @Override
    public DataResult<Page<AdvertResponse>> getAllAdvert(Integer page,Integer size) {
        Pageable paging = PageRequest.of(page-1, size);
        return new SuccessDataResult(jobAdvertisementDao.findAll(paging).map((advert) ->
                createAdvertResponse(advert)), "Job adverts listed");
    }

    @Override
    public DataResult<Page<AdvertResponse>> getJobAdvertByCompanyNameAndJobConditionIsTrue(String email,
                                                                                           Pageable pageable) {
        Page<JobAdvertisement> jobAdvertisements = jobAdvertisementDao.getByJobConditionIsTrueAndEmployer_Email(email, pageable);
        if (!jobAdvertisements.isEmpty()) {
            return new SuccessDataResult(jobAdvertisements.map((advert) -> createAdvertResponse(advert)), "list job advert of Company");
        }
        return new ErrorDataResult("not found active job advert");
    }

    @Override
    public Result jobAdvertisementActive(long id, String companyName) {
        JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.getByIdAndEmployer_CompanyName(id, companyName);
        if (jobAdvertisement != null) {
            jobAdvertisement.setJobCondition(!jobAdvertisement.isJobCondition());
            jobAdvertisementDao.save(jobAdvertisement);
            return new SuccessResult("update job advert");
        }
        return new ErrorResult("not found job advert by this name");
    }


    @Override
    public Result deleteJobAdvert(long advertId) {
        Optional<JobAdvertisement> jobPosition = this.jobAdvertisementDao.findById(advertId);
        if (jobPosition.isPresent()) {
            this.jobAdvertisementDao.deleteById(jobPosition.get().getId());
            return new SuccessResult("delete job advertisement");
        }
        return new ErrorResult("not found job advertisement");
    }

    @Override
    public DataResult<JobAdvertisement> getAdvertById(long advertId) {
        Optional<JobAdvertisement> jobAdvert = this.jobAdvertisementDao.findById(advertId);
        if (jobAdvert.isPresent()) {
            return new SuccessDataResult(jobAdvert.get(), "job posting status changed successfully!");
        }
        return new ErrorDataResult("not found job advert!");
    }

    private Set<City> findCitiesForAdvert(Set<String> advertCities) {
        Set<City> cities = new HashSet<>();
        advertCities.stream().forEach(city -> {
            DataResult<City> cityList = cityService.getCityByCityName(city);
            if (cityList.isSuccess()) {
                cities.add(cityList.getData());
            }
        });
        return cities;
    }

    @Override
    public DataResult<AdvertResponse> changeStatusOfJobAdvert(long advertId) {
        Optional<JobAdvertisement> jobAdvert = this.jobAdvertisementDao.findById(advertId);
        if (jobAdvert.isPresent()) {
            JobAdvertisement updateJobAdvert = jobAdvert.get();
            boolean advertCondition = updateJobAdvert.isJobCondition();
            updateJobAdvert.setJobCondition(!advertCondition);
            updateJobAdvert.setId(jobAdvert.get().getId());
            jobAdvertisementDao.save(updateJobAdvert);
            return new SuccessDataResult(createAdvertResponse(updateJobAdvert), "job posting status changed successfully!");
        }

        return new ErrorDataResult("not found job advert!");
    }

    @Override
    public DataResult<AdvertResponse> updateAdvert(JobAdvertisement jobAdvertisement) {
        return new SuccessDataResult(jobAdvertisementDao.save(jobAdvertisement), "Success");
    }

    private JobAdvertisement convertDtoObjectForAdvert(JobAdvertisementRequest jobAdvertisementRequest, Employer employer, Set<City> cities, JobPosition jobPosition, WorkTime workTime, WorkType workType) {
        return advertDto.advertDto(jobAdvertisementRequest, employer, cities, jobPosition, workTime, workType);
    }

    private DataResult<Employer> getEmployerFromService(String email) {
        return employerService.findEmployerByEmail(email);
    }

    private DataResult<JobPosition> getJobPositionFromService(String jobName) {
        return jobPositionService.getJobPositionByJobName(jobName);
    }

    private DataResult<WorkTime> getWorkTimeFromService(Integer id) {
        return workTimeManager.getWorkTimeById(id);
    }

    private DataResult<WorkType> getWorkTypeFromService(Integer id) {
        return workTypeManager.getWorkTypeById(id);
    }

    private AdvertResponse createAdvertResponse(JobAdvertisement advert) {

        return AdvertResponse.builder().jobName(advert.getJobPosition().getJobName())
                .applicationDeadline(advert.getApplicationDeadline())
                .email(advert.getEmployer().getEmail())
                .minSalary(advert.getMinSalary())
                .maxSalary(advert.getMaxSalary())
                .openPositions(advert.getOpenPositions())
                .jobCondition(advert.isJobCondition())
                .cities(advert.getCity())
                .releaseDate(advert.getReleaseDate())
                .workTime(advert.getWorkTime().getTimeName())
                .workType(advert.getWorkType().getTypeName())
                .jobInform(advert.getJobInform())
                .companyName(advert.getEmployer().getCompanyName()).build();
    }
}
