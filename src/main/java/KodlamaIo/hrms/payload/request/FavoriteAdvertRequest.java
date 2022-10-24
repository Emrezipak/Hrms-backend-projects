package KodlamaIo.hrms.payload.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteAdvertRequest {

    private Long advertsId;
    private String jobSeekerEmail;
}
