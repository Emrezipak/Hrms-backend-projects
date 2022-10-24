package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.EmployerService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.EmployerDao;
import KodlamaIo.hrms.entity.concretes.Employer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployerManager implements EmployerService {

	private EmployerDao employerDao;

	public EmployerManager(EmployerDao employerDao) {
		this.employerDao = employerDao;
	}
	
	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult(employerDao.findAll(),"listed employers");
	}

	@Override
	public Result save(Employer user) {
		this.employerDao.save(user);
		return new SuccessResult("added new employer");
	}

	private boolean controlEmail(String email) {
		Optional<Employer> employer=this.employerDao.getEmployerByEmail(email);
		if(employer.isPresent()) {
			return true;
		}
		return false;
	}

	@Override
	public Result deleteEmployer(String email) {
		Optional<Employer> employer=this.employerDao.getEmployerByEmail(email);
		if(employer.isPresent()){
			employerDao.deleteById(employer.get().getId());
			return new SuccessResult("delete employer");
		}
		return new ErrorResult("not found employer");
	}

	@Override
	public DataResult<Employer> updateEmployer(Long id, Employer newEmployer) {
		Optional<Employer> employer=this.employerDao.findById(id);
		if(employer.isPresent()){
			Employer updateEmployer=employer.get();
			updateEmployer.setCompanyName(newEmployer.getCompanyName());
			updateEmployer.setEmail(newEmployer.getEmail());
			updateEmployer.setWebsite(newEmployer.getWebsite());
			updateEmployer.setPassword(newEmployer.getPassword());
			updateEmployer.setPhoneNumber(newEmployer.getPhoneNumber());
			this.employerDao.save(updateEmployer);
			return new SuccessDataResult(updateEmployer,"updated employer");
		}
		return new ErrorDataResult("not found employer");
	}

	@Override
	public DataResult<List<Employer>> getEmployerByCompanyName(String companyName) {
		List<Employer> employerList=this.employerDao.getEmployerByCompanyName(companyName);
		return new SuccessDataResult(employerList);
	}

	@Override
	public DataResult<Employer> getEmployerByEmail(String email) {
		Optional<Employer> employer = this.employerDao.getEmployerByEmail(email);
		if(employer.isPresent()){
			return new SuccessDataResult(employer,"find the company");
		}
		return new ErrorDataResult("not found Employer");
	}

	@Override
	public DataResult<Employer> findEmployerByEmail(String email) {
		Optional<Employer> employer = this.employerDao.getEmployerByEmail(email);
		if(employer.isPresent()){
			return new SuccessDataResult(employer.get(),"Success");
		}
		return new ErrorDataResult("not found employer");
	}


}
