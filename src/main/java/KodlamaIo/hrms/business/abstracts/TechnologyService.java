package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.entity.concretes.Technology;

import java.util.List;

public interface TechnologyService{

    DataResult<Technology> add(Technology skill);

    DataResult<List<Technology>> getAllTechnologies();

    DataResult<Technology> getTechnologiesByTechnologyName(String technology);


}
