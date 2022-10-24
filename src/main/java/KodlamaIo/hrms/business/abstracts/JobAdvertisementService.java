package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.JobAdvertisement;
import KodlamaIo.hrms.payload.request.JobAdvertisementRequest;
import KodlamaIo.hrms.payload.response.AdvertResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobAdvertisementService {

	DataResult<JobAdvertisement> createAdvertisement(JobAdvertisementRequest jobAdvertisementRequest);
	DataResult<Page<AdvertResponse>> getJobAdvertsByActive(Pageable pageable);
	DataResult<Page<AdvertResponse>> getByJobConditionTrueOrderByReleaseDateDesc(Pageable pageable);

	DataResult<Page<AdvertResponse>> getAllAdvert(Integer page,Integer size);
	
	DataResult<Page<AdvertResponse>> getJobAdvertByCompanyNameAndJobConditionIsTrue(String companyId,Pageable pageable);

	Result jobAdvertisementActive(long id,String companyName);
    //boolean controlEmployerId(long id);
	Result deleteJobAdvert(long advertId);

	DataResult<JobAdvertisement> getAdvertById(long advertId);
	DataResult<AdvertResponse> changeStatusOfJobAdvert(long advertId);

	DataResult<AdvertResponse> updateAdvert(JobAdvertisement jobAdvertisement);
}
