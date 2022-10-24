package KodlamaIo.hrms.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.School;
import org.springframework.data.jpa.repository.Query;

public interface SchoolDao extends JpaRepository<School,Long> {

	@Query("From School s where s.jobSeekerCv.cvId =:id ORDER BY s.finishYear DESC")
	List<School> getByJobSeekerCv_CvIdOrderByFinishYearDesc(Long id);
	List<School> getByJobSeekerCv_CvId(Long id);

}
