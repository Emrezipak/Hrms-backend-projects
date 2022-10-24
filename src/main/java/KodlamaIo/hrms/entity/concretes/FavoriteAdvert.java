package KodlamaIo.hrms.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "favorite")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="favorite_id")
    private Long id;

    @ManyToMany
    private Set<JobAdvertisement> jobAdvertisementList;
}
