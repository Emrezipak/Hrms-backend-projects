package KodlamaIo.hrms.dtos;

import KodlamaIo.hrms.core.messages.ResponseMessages;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import KodlamaIo.hrms.entity.concretes.School;
import KodlamaIo.hrms.payload.request.SchoolCreateRequest;
import lombok.Data;

@Data
public class SchoolDto {

    public School createSchoolObject(SchoolCreateRequest schoolCreateRequest, JobSeekerCv jobSeekerCv) {
        return School.builder().schoolName(schoolCreateRequest.getSchoolName())
                .departmentName(schoolCreateRequest.getDepartmentName())
                .startsYear(schoolCreateRequest.getStartsYear())
                .finishYear(schoolCreateRequest.getFinishYear() != null &&
                        !schoolCreateRequest.getFinishYear().isEmpty()
                        ? schoolCreateRequest.getFinishYear() : ResponseMessages.MESSAGE)
                .jobSeekerCv(jobSeekerCv).build();
    }
}
