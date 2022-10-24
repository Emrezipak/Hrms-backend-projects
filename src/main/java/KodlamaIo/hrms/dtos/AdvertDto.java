package KodlamaIo.hrms.dtos;

import KodlamaIo.hrms.entity.concretes.*;
import KodlamaIo.hrms.payload.request.JobAdvertisementRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
public class AdvertDto {

    public JobAdvertisement advertDto(JobAdvertisementRequest jobAdvertisementRequest,
                                      Employer employer, Set<City> cities,
                                      JobPosition jobPosition,
                                      WorkTime workTime, WorkType workType) {
        return JobAdvertisement.builder().jobPosition(jobPosition)
                .city(cities)
                .employer(employer)
                .applicationDeadline(jobAdvertisementRequest.getApplicationDeadline())
                .releaseDate(jobAdvertisementRequest.getReleaseDate() != null ?
                        jobAdvertisementRequest.getReleaseDate() : LocalDate.now())
                .minSalary(jobAdvertisementRequest.getMinSalary())
                .maxSalary(jobAdvertisementRequest.getMaxSalary())
                .openPositions(jobAdvertisementRequest.getOpenPositions())
                .workTime(workTime)
                .jobInform(jobAdvertisementRequest.getJobInform())
                .jobCondition(true)
                .workType(workType).build();


    }

}
