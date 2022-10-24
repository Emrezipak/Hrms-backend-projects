package KodlamaIo.hrms.config;

import KodlamaIo.hrms.dtos.AdvertDto;
import KodlamaIo.hrms.dtos.BaseCvDto;
import KodlamaIo.hrms.dtos.ExperienceDto;
import KodlamaIo.hrms.dtos.SchoolDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DtoBean {


    @Bean
    public ExperienceDto experienceDto(){
        return new ExperienceDto();
    }
    @Bean
    public SchoolDto schoolDto(){
        return new SchoolDto();
    }

    @Bean
    public BaseCvDto baseCvDto(){
        return new BaseCvDto();
    }

    @Bean
    public AdvertDto baseAdvertDto(){
        return new AdvertDto();
    }


}
