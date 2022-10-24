package KodlamaIo.hrms.business.abstracts;


import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.City;

import java.util.List;

public interface CityService {

    Result addCity(City city);
    DataResult<List<City>> getAllCity();
    Result deleteCity(long id);
    DataResult<City> updateCity(long id,City city);
    DataResult<City> getCityByCityName(String cityName);
}
