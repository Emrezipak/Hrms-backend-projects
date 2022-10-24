package KodlamaIo.hrms.entity.concretes;


import java.time.LocalDate;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "job_advertisement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobAdvertisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_position")
    private JobPosition jobPosition;

    @ManyToMany
    @JoinTable(name = "advert_city",
            joinColumns = @JoinColumn(name = "advert_id"),
            inverseJoinColumns = @JoinColumn(name = "city_id"))
    private Set<City> city;

    @Column(name = "min_salary")
    private Integer minSalary;

    @Column(name = "max_salary")
    private Integer maxSalary;

    @Column(name = "job_condition")
    private boolean jobCondition = true;

    //@Temporal(TemporalType.DATE)
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "application_deadline")
    private LocalDate applicationDeadline;

    @ManyToOne
    @JoinColumn(name = "company_name")
    private Employer employer;

    //@Size(min = 1)
    @Column(name = "open_positions")
    private Integer openPositions;

    //@Temporal(TemporalType.DATE)
    //@JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "job_inform")
    private String jobInform;

    @OneToOne
    @JoinColumn(name = "work_time_id")
    private WorkTime workTime;

    @OneToOne
    @JoinColumn(name = "work_type_id")
    private WorkType workType;

    @ManyToMany
    @JoinTable(name = "favorite_advert",
            joinColumns = @JoinColumn(name = "advert_id"),
            inverseJoinColumns = @JoinColumn(name = "favorite_id"))
    private Set<FavoriteAdvert> favoriteAdverts;

}
