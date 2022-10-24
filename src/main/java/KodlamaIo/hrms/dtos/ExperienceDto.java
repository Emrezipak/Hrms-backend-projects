package KodlamaIo.hrms.dtos;

import KodlamaIo.hrms.core.messages.ResponseMessages;
import KodlamaIo.hrms.entity.concretes.Experience;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import KodlamaIo.hrms.payload.request.ExperienceCreateRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ExperienceDto {


    public Experience createExperienceObject(ExperienceCreateRequest experienceRequest, JobSeekerCv jobSeekerCv) {
        return Experience.builder().workplaceName(experienceRequest.getWorkplaceName())
                .jobPosition(experienceRequest.getJobPosition())
                .startsYear(experienceRequest.getStartsYear())
                .finishYear(experienceRequest.getFinishYear() != null &&
                        !experienceRequest.getFinishYear().isEmpty()
                        ? experienceRequest.getFinishYear() : ResponseMessages.MESSAGE)
                .jobStatus(experienceRequest.getFinishYear().isEmpty())
                .jobSeekerCv(jobSeekerCv).build();
    }
}
