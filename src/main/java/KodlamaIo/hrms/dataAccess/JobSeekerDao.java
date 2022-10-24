package KodlamaIo.hrms.dataAccess;

import java.util.List;
import java.util.Optional;

import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.JobSeeker;

public interface JobSeekerDao extends JpaRepository<JobSeeker,Long>{

	Optional<JobSeeker> getJobSeekerByEmail(String Email);
	Optional<JobSeeker> getJobSeekerByidentificationNo(String nationalIdentity);
	JobSeeker getById(long id);
}
