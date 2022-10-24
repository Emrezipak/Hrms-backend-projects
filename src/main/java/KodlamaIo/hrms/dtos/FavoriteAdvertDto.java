package KodlamaIo.hrms.dtos;

import KodlamaIo.hrms.entity.concretes.FavoriteAdvert;
import KodlamaIo.hrms.entity.concretes.JobAdvertisement;
import KodlamaIo.hrms.entity.concretes.JobSeeker;
import KodlamaIo.hrms.payload.request.FavoriteAdvertRequest;
import lombok.Data;

@Data
public class FavoriteAdvertDto {

    public FavoriteAdvert getFavoriteAdvert(JobAdvertisement jobAdvertisement, JobSeeker jobSeeker){
        return FavoriteAdvert.builder()
                .jobSeeker(jobSeeker)
                .jobAdvertisement(jobAdvertisement).build();
    }
}
