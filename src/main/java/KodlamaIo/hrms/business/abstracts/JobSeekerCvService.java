package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import KodlamaIo.hrms.payload.request.CvBaseRequest;
import KodlamaIo.hrms.payload.request.CvRequest;
import KodlamaIo.hrms.payload.response.CvBaseResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobSeekerCvService{

	DataResult<CvBaseResponse> createCv(CvBaseRequest cvRequest);
	DataResult<JobSeekerCv> updateCv(CvRequest cvRequest,Long cvId);
	DataResult<List<JobSeekerCv>> getByIdCv(long id,String identificationNo);
	JobSeekerCv getByJobSeekerCvId(long id);
	DataResult<List<JobSeekerCv>> getAllCvByUserId(Long jobSeekerId);
	DataResult<List<JobSeekerCv>> getAllCvs();




}
