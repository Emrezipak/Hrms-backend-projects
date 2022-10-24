package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.CityService;
import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.City;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/city")
@CrossOrigin
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/getAllCity")
    public Result getAllCity() {
        return this.cityService.getAllCity();
    }

    @PostMapping("/addCity")
    public Result addCity(@RequestBody @Valid City city) {
        return this.cityService.addCity(city);
    }

    @GetMapping("/getByCityId")
    public Result getCityByCityName(@RequestParam String cityName) {
        return this.cityService.getCityByCityName(cityName);
    }

    @DeleteMapping("/deleteCity/{id}")
    public Result deleteCity(@PathVariable long id) {
        return this.cityService.deleteCity(id);
    }

    @PutMapping("/updateCity/{id}")
    public Result updateCity(@PathVariable long id, @RequestBody City city) {
        return this.cityService.updateCity(id, city);
    }
}
