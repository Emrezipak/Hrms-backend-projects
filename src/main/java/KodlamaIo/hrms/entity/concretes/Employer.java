package KodlamaIo.hrms.entity.concretes;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import KodlamaIo.hrms.entity.abstracts.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="employer")
@AllArgsConstructor
@NoArgsConstructor
public class Employer extends User {

	@NotEmpty
	@Column(name="company_name")
	private String companyName;

	@NotEmpty
	@Column(name="website")
	private String website;

	@NotEmpty
	@Column(name="phone_number")
	private String phoneNumber;

}
