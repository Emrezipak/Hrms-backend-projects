package KodlamaIo.hrms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteAdvertRequest {

    @NotNull
    private Long advertsId;
    @NotEmpty
    private String jobSeekerEmail;
}
