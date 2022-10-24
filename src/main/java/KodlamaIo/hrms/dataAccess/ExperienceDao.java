package KodlamaIo.hrms.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.Experience;
import org.springframework.data.jpa.repository.Query;

public interface ExperienceDao extends JpaRepository<Experience, Long> {

	@Query("From Experience c where c.jobSeekerCv.cvId =:id ORDER BY c.finishYear DESC")
	List<Experience> getByJobSeekerCv_CvIdOrderByFinishYear(long id);
	List<Experience> getByJobSeekerCv_CvId(long id);
	//JobSeeker in içindeki id ulaşmamız gerekiyor...
}
