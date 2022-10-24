package KodlamaIo.hrms.entity.concretes;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "job_seekers_cv")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobSeekerCv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long cvId;

    @Column(name = "explanation")
    private String explanation;

    @Column(name = "linkedlnAddress")
    private String linkedlnAddress;

    @Column(name = "githubAddress")
    private String githubAddress;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id")
    private JobSeeker jobSeeker;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @OneToMany(mappedBy = "jobSeekerCv",cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @ManyToMany
    @JoinTable(name = "cv_foreignLanguage",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Set<ForeignLanguage> languages;

    @OneToMany(mappedBy = "jobSeekerCv",cascade = CascadeType.ALL)
    private List<School> schools;

    @ManyToMany
    @JoinTable(name = "cv_technologies",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id"))
    private Set<Technology> technologies;
}