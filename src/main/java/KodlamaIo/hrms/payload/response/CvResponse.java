package KodlamaIo.hrms.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CvResponse {

    private String username;
    private String surname;
    private String email;
    private String birthYear;
    private String imageUrl;
    private String explanation;
    private String linkedLnAddress;
    private String githubAddress;
}
