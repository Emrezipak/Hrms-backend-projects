package KodlamaIo.hrms.dtos;

import KodlamaIo.hrms.entity.concretes.*;
import KodlamaIo.hrms.payload.request.CvBaseRequest;
import lombok.*;

@Data
public class BaseCvDto {

    public JobSeekerCv getJobSeekerCv(CvBaseRequest cvBaseRequest, JobSeeker jobSeeker) {
        return JobSeekerCv.builder()
                .explanation(cvBaseRequest.getExplanation())
                .jobSeeker(jobSeeker).build();
    }

}
