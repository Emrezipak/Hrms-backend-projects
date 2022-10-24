package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.CityService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.CityDao;
import KodlamaIo.hrms.entity.concretes.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityManager implements CityService {

    private CityDao cityDao;

    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Result addCity(City city) {
        if (cityDao.existsByCityNameIgnoreCase(city.getCityName())) {
            return new ErrorResult("city with this name exists");
        }
        this.cityDao.save(city);
        return new SuccessResult("added new city");
    }

    @Override
    public DataResult<List<City>> getAllCity() {

        return new SuccessDataResult<List<City>>(this.cityDao.findAll(), "listed cities");
    }

    @Override
    public Result deleteCity(long id) {
        Optional<City> city = this.cityDao.findById(id);
        if (city.isPresent()) {
            this.cityDao.deleteById(id);
            return new SuccessResult("delete the city");
        }
        return new ErrorResult("not found city");
    }

    @Override
    public DataResult<City> updateCity(long id, City newCity) {
        Optional<City> city = this.cityDao.findById(id);
        if (city.isPresent()) {
            City updateCity = city.get();
            updateCity.setCityName(newCity.getCityName());
            this.cityDao.save(city.get());
            return new SuccessDataResult(city.get(), "update city");
        }
        return new ErrorDataResult(city.get(), "not found city");
    }

    @Override
    public DataResult<City> getCityByCityName(String cityName) {
        Optional<City> city = this.cityDao.getCityByCityName(cityName);
        if (city.isPresent()) {
            return new SuccessDataResult(city.get(), "found city");
        }
        return new ErrorDataResult("not found city");
    }


    private boolean findCityName(String cityName) {
        if (this.cityDao.getCityByCityName(cityName).isPresent()) {
            return true;
        }
        return false;
    }


}
