package KodlamaIo.hrms.dataAccess;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement,Long> {

	Page<JobAdvertisement> getByJobConditionIsTrue(Pageable pageable);
	
	Page<JobAdvertisement> getByJobConditionIsTrueOrderByReleaseDateDesc(Pageable pageable);
	
	Page<JobAdvertisement> getByJobConditionIsTrueAndEmployer_Email(String companyId,Pageable pageable);
	
	JobAdvertisement getByIdAndEmployer_CompanyName(long id,String companyName);

	//@Query("UPDATE set ")
	//JobAdvertisement changeStatusOfJobAdvert(long jobAdvertId);


	
	
}
	

