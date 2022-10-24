package KodlamaIo.hrms.dataAccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.JobPosition;
import org.springframework.data.jpa.repository.Query;

public interface JobPositionDao extends JpaRepository<JobPosition,Long>{
	@Query(value = "select * from job_positions where job_name like %?1%",nativeQuery = true)
	List<JobPosition> getJobPositionsByJobName(String jobName);
	Optional<JobPosition> getJobPositionByJobName(String jobName);
}
