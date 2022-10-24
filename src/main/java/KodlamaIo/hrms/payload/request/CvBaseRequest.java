package KodlamaIo.hrms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CvBaseRequest {

    @NotEmpty
    private String jobSeekerEmail;

    @NotEmpty
    private String explanation;
}
