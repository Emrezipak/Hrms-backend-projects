package KodlamaIo.hrms.dataAccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.Technology;

public interface TechnologyDao extends JpaRepository<Technology,Integer>{

	//List<Technology> getByJobSeekerCv_id(long id);
    Optional<Technology> getTechnologiesByTechnologyNameIgnoreCase(String technologyName);


}
