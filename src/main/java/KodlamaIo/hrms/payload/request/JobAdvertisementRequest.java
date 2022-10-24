package KodlamaIo.hrms.payload.request;

import KodlamaIo.hrms.entity.concretes.WorkTime;
import KodlamaIo.hrms.entity.concretes.WorkType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Lob;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobAdvertisementRequest {

    @NotEmpty
    private String jobName;

    @NotNull
    @Size(min = 1)
    private Set<String> cities;

    @NotNull
    @Min(1)
    private Integer minSalary;

    @NotNull
    @Min(1)
    private Integer maxSalary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Future
    @NotNull
    private LocalDate applicationDeadline;

    @NotEmpty
    private String companyEmail;

    @NotNull
    private Integer openPositions;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Nullable
    @Future
    private LocalDate releaseDate;

    @Lob
    @NotEmpty
    private String jobInform;

    @NotNull
    private Integer workTimeId;

    @NotNull
    private Integer workTypeId;

}
