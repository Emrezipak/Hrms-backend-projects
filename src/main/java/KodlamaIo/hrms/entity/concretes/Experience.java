package KodlamaIo.hrms.entity.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

import lombok.*;

@Entity
@Table(name="experiences")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Experience {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long experienceId;
	
	@Column(name="workplace_name")
	private String workplaceName;
	
	@Column(name="job_position")
	private String jobPosition;

	@Column(name="starts_job_year")
	private String startsYear;

	@Column(name="finish_job_year")
	private String finishYear;

	@Column(name="experience_status")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private boolean jobStatus;
	@ManyToOne
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private JobSeekerCv jobSeekerCv;
}
