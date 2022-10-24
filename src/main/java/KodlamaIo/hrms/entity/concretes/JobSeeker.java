package KodlamaIo.hrms.entity.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import KodlamaIo.hrms.entity.abstracts.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Entity
@Table(name="job_seekers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobSeeker extends User {

	@Column(name="username")
	private String userName;
	
	@Column(name="user_surname")
	private String userSurname;
	
	@Column(name="identification_no")
	private String identificationNo;

	@Column(name="birth_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthYear;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@OneToMany(mappedBy = "jobSeeker")
	private List<JobSeekerCv> jobSeekerCv;

	@OneToOne(cascade = {CascadeType.REMOVE,CascadeType.REFRESH,CascadeType.MERGE,CascadeType.DETACH})
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JoinColumn(name="image")
	private Image image;

}
