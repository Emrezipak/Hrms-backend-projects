package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Employer;

import java.util.List;

public interface EmployerService {
	Result deleteEmployer(String email);
	DataResult<Employer> updateEmployer(Long id,Employer employer);
	DataResult<List<Employer>> getEmployerByCompanyName(String companyName);
	DataResult<Employer> getEmployerByEmail(String email);
	DataResult<Employer> findEmployerByEmail(String email);
	DataResult<List<Employer>> getAll();
	Result save(Employer user);

}
