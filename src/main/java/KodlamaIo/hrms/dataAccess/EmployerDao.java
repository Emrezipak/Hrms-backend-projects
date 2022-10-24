package KodlamaIo.hrms.dataAccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.Employer;
import org.springframework.data.jpa.repository.Query;

public interface EmployerDao extends JpaRepository<Employer,Long> {
   
	List<Employer> findAllByEmail(String email);
	Employer getById(long id);
	Optional<Employer> getEmployerByEmail(String email);
	//JPQL @Query(value = " from Employer where companyName like %:companyName%")
	@Query(value = "Select * from Employer where company_name like %?1% ",nativeQuery = true)
	List<Employer> getEmployerByCompanyName(String companyName);
}
