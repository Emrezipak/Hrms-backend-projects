package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.FavoriteAdvertService;
import KodlamaIo.hrms.business.abstracts.JobAdvertisementService;
import KodlamaIo.hrms.business.abstracts.JobSeekerService;
import KodlamaIo.hrms.core.utilities.results.*;
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
import java.util.stream.Stream;

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
        return new SuccessDataResult(favoriteAdvertDao.findAll(),"Success");
    }

    @Override
    public DataResult<FavoriteAdvert> addFavorite(FavoriteAdvertRequest favoriteAdvertRequest) {
        DataResult<JobAdvertisement> jobAdvertisement = jobAdvertisementService.getAdvertById(favoriteAdvertRequest.getAdvertsId());
        DataResult<JobSeeker> jobSeeker = jobSeekerService.getJobSeekerByEmail(favoriteAdvertRequest.getJobSeekerEmail());
        List<FavoriteAdvert> favoriteAdverts = favoriteAdvertDao.getFavoriteAdvertByJobSeeker_Email(favoriteAdvertRequest.getJobSeekerEmail());
        Optional<FavoriteAdvert> duplicateFavoriteAdvert = favoriteContainsAdvertId(favoriteAdverts,
                favoriteAdvertRequest.getAdvertsId());

        if (!jobAdvertisement.isSuccess()) {
            return new ErrorDataResult("not found advert");
        } else if (!jobSeeker.isSuccess()) {
            return new ErrorDataResult("not found user");
        } else if (duplicateFavoriteAdvert.isPresent()) {
            favoriteAdvertDao.deleteById(duplicateFavoriteAdvert.get().getId());
            return new ErrorDataResult("removed from favorites");
        }

        FavoriteAdvert favoriteAdvert = favoriteRequestToDto(jobAdvertisement.getData(), jobSeeker.getData());
        favoriteAdvertDao.save(favoriteAdvert);
        return new SuccessDataResult(createFavoriteResponse(favoriteAdvert), "the advert added to favorite");
    }

    @Override
    public Result deleteFavorite(Long id) {
        Optional<FavoriteAdvert> favoriteAdvert = favoriteAdvertDao.findById(id);
        if (!favoriteAdvert.isPresent()) {
            return new ErrorResult("not found favorite");
        }
        favoriteAdvertDao.deleteById(id);
        return new SuccessResult("removed favorite");
    }

    @Override
    public DataResult<List<FavoriteAdvertResponse>> getFavoriteAdvertByJobSeekerEmail(String email) {
        DataResult<JobSeeker> jobSeeker = jobSeekerService.getJobSeekerByEmail(email);
        if (!jobSeeker.isSuccess()) {
            return new ErrorDataResult("not found user");
        }
        return new SuccessDataResult(favoriteAdvertDao.getFavoriteAdvertByJobSeeker_Email(email).stream().map(
                favoriteAdvert -> createFavoriteResponse(favoriteAdvert)), "Success");
    }

    private FavoriteAdvert favoriteRequestToDto(JobAdvertisement jobAdvertisement, JobSeeker jobSeeker) {
        return favoriteAdvertDto.getFavoriteAdvert(jobAdvertisement, jobSeeker);
    }

    private FavoriteAdvertResponse createFavoriteResponse(FavoriteAdvert favoriteAdvert) {
        return FavoriteAdvertResponse.builder()
                .favoriteId(favoriteAdvert.getId())
                .jobAdvertisement(favoriteAdvert.getJobAdvertisement())
                .email(favoriteAdvert.getJobSeeker().getEmail()).build();
    }

    private Optional<FavoriteAdvert> favoriteContainsAdvertId(List<FavoriteAdvert> favoriteAdverts, Long id) {
        return favoriteAdverts.stream().filter(advert -> advert.getJobAdvertisement().getId() == id).findAny();
    }
}
