package KodlamaIo.hrms.entity.concretes;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import java.util.Set;

@Entity
@Table(name="technologies")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Technology {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long technologyId;
	
	@Column(name="technology_name")
	private String technologyName;


}
