package KodlamaIo.hrms.payload.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@RequiredArgsConstructor
public class ExperienceCreateRequest {

    @NotEmpty
    private String workplaceName;

    @NotEmpty
    private String jobPosition;

    @NotEmpty
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String startsYear;

    @Nullable
    private String finishYear;

    @NotNull
    private Long jobSeekerCvId;

}
