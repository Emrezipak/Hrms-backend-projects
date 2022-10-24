package KodlamaIo.hrms.dataAccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.JobSeekerCv;

public interface JobSeekerCvDao extends JpaRepository<JobSeekerCv, Long> {

	List<JobSeekerCv> getByCvIdOrJobSeeker_identificationNo(Long id,String identificationNo);
	Optional<JobSeekerCv> getJobSeekerCvByCvId(Long id);

	List<JobSeekerCv> getAllByJobSeeker_Id(Long jobSeekerId);
	

}
