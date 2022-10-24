package KodlamaIo.hrms.entity.concretes;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="cities")
public class City {
	
	@Id
	@Column(name="city_id")
	private Long id;

	@NotEmpty
	@Column(name="city_name",unique = true)
	private String cityName;


}
