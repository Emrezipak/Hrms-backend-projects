package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.TechnologyService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.TechnologyDao;
import KodlamaIo.hrms.entity.concretes.Technology;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnologyManager implements TechnologyService {

	private TechnologyDao technologyDao;
	private CvIdControl cvIdControl;

	public TechnologyManager(TechnologyDao technologyDao,CvIdControl cvIdControl) {
		this.technologyDao = technologyDao;
		this.cvIdControl=cvIdControl;
	}

	@Override
	public DataResult<Technology> add(Technology newSkill) {
			this.technologyDao.save(newSkill);
			return new SuccessDataResult<>(newSkill,"added new technology");
	}

	@Override
	public DataResult<List<Technology>> getAllTechnologies() {
		return new SuccessDataResult<List<Technology>>
		(this.technologyDao.findAll(),"skills listed");
	}

	/**
	 * this section will change
	 */

	@Override
	public DataResult<Technology> getTechnologiesByTechnologyName(String technologyName) {
		Optional<Technology> technology = technologyDao.getTechnologiesByTechnologyNameIgnoreCase(technologyName);
		if(!technology.isPresent()){
			Technology newTechnology = createNewTechnology(technologyName);
			technologyDao.save(newTechnology);
			return new SuccessDataResult<>(newTechnology,"data successfully saved");
		}
		return new SuccessDataResult<>(technology.get(),"data successfully retrieved");
	}

	private Technology createNewTechnology(String technologyName){
		return Technology.builder().technologyName(technologyName).build();
	}

}
