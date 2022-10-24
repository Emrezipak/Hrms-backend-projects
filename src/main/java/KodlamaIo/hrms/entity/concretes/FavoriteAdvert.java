package KodlamaIo.hrms.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "favorite")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoriteAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="favorite_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="advert_id")
    private JobAdvertisement jobAdvertisement;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;
}
