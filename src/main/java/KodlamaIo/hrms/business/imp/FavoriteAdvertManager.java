package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.FavoriteAdvertService;
import KodlamaIo.hrms.business.abstracts.JobAdvertisementService;
import KodlamaIo.hrms.business.abstracts.JobSeekerService;
import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.ErrorDataResult;
import KodlamaIo.hrms.core.utilities.results.SuccessDataResult;
import KodlamaIo.hrms.dataAccess.FavoriteAdvertDao;
import KodlamaIo.hrms.dtos.FavoriteAdvertDto;
import KodlamaIo.hrms.entity.concretes.FavoriteAdvert;
import KodlamaIo.hrms.entity.concretes.JobAdvertisement;
import KodlamaIo.hrms.entity.concretes.JobSeeker;
import KodlamaIo.hrms.payload.request.FavoriteAdvertRequest;
import KodlamaIo.hrms.payload.response.FavoriteAdvertResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteAdvertManager implements FavoriteAdvertService {

    private FavoriteAdvertDao favoriteAdvertDao;
    private JobSeekerService jobSeekerService;

    private JobAdvertisementService jobAdvertisementService;

    private FavoriteAdvertDto favoriteAdvertDto;

    public FavoriteAdvertManager(FavoriteAdvertDao favoriteAdvertDao,
                                 JobSeekerService jobSeekerService,
                                 JobAdvertisementService jobAdvertisementService,
                                 FavoriteAdvertDto favoriteAdvertDto) {
        this.favoriteAdvertDao = favoriteAdvertDao;
        this.jobSeekerService = jobSeekerService;
        this.jobAdvertisementService = jobAdvertisementService;
        this.favoriteAdvertDto = favoriteAdvertDto;
    }

    @Override
    public DataResult<List<FavoriteAdvert>> getAllFavoriteAdvert() {
        return null;
    }

    @Override
    public DataResult<FavoriteAdvert> addFavorite(FavoriteAdvertRequest favoriteAdvertRequest) {
        DataResult<JobAdvertisement> jobAdvertisement = jobAdvertisementService.getAdvertById(favoriteAdvertRequest.getAdvertsId());
        DataResult<JobSeeker> jobSeeker = jobSeekerService.getById(favoriteAdvertRequest.getAdvertsId());
        if (!jobAdvertisement.isSuccess()) {
            return new ErrorDataResult("not found advert");
        }
        else if (!jobSeeker.isSuccess()) {
            return new ErrorDataResult("not found user");
        }
        FavoriteAdvert favoriteAdvert = favoriteRequestToDto(jobAdvertisement.getData(), jobSeeker.getData());
        favoriteAdvertDao.save(favoriteAdvert);
        return new SuccessDataResult("the advert added to favorite");
    }

    @Override
    public DataResult<FavoriteAdvert> deleteFavorite() {
        return null;
    }

    @Override
    public DataResult<List<FavoriteAdvert>> getFavoriteAdvertByJobSeekerEmail(String email) {
        DataResult<JobSeeker> jobSeeker = jobSeekerService.getJobSeekerByEmail(email);
        if (jobSeeker.isSuccess()) {
            return new SuccessDataResult(favoriteAdvertDao.getFavoriteAdvertByJobSeeker_Email(email), "Success");
        }
        return new ErrorDataResult("not found user");
    }

    private FavoriteAdvert favoriteRequestToDto(JobAdvertisement jobAdvertisement,JobSeeker jobSeeker){
        return favoriteAdvertDto.getFavoriteAdvert(jobAdvertisement,jobSeeker);
    }
    private FavoriteAdvertResponse createFavoriteResponse(FavoriteAdvert favoriteAdvert){
        return FavoriteAdvertResponse.builder().
                favoriteId(favoriteAdvert.getId()).build();
    }
}
