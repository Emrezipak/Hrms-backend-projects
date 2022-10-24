package KodlamaIo.hrms.entity.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JobAdvertisement jobAdvertisement;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private JobSeeker jobSeeker;
}
