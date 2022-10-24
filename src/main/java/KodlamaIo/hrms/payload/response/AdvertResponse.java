package KodlamaIo.hrms.payload.response;

import KodlamaIo.hrms.entity.concretes.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertResponse {

    private String jobName;

    private String jobInform;

    private Set<City> cities;

    private Integer minSalary;

    private Integer maxSalary;

    private boolean jobCondition;

    private LocalDate applicationDeadline;

    private String companyName;

    private String email;

    private Integer openPositions;

    private LocalDate releaseDate;

    private String workTime;

    private String workType;
}
