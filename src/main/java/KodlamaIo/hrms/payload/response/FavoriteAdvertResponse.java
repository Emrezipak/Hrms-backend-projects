package KodlamaIo.hrms.payload.response;

import KodlamaIo.hrms.entity.concretes.JobAdvertisement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteAdvertResponse {

    private Long favoriteId;
    private JobAdvertisement jobAdvertisement;
    private String email;
}
