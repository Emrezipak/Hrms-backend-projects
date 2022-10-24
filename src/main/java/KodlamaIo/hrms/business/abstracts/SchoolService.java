package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.School;
import KodlamaIo.hrms.payload.request.SchoolCreateRequest;

import java.util.List;

public interface SchoolService{

	DataResult<School> addSchoolInformation(SchoolCreateRequest newSchool);

	DataResult<List<School>> getByFinishSchoolYear(Long id);

	DataResult<List<School>> getAllSchool();

	DataResult<List<School>> getByJobSeeker(Long id);

	Result deleteSchool(Long schoolId);

}
