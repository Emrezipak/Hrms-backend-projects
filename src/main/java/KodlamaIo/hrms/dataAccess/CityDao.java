package KodlamaIo.hrms.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.City;

import java.util.Optional;

public interface CityDao extends JpaRepository<City, Long>{

    Optional<City> getCityByCityName(String cityName);
    boolean existsByCityNameIgnoreCase(String name);
}
