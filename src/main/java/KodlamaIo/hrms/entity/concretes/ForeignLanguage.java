package KodlamaIo.hrms.entity.concretes;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

import java.util.Set;

@Entity
@Table(name="foreign_languages")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForeignLanguage {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long languageId;
	
	@NotEmpty
	@Column(name="language_name")
	private String languageName;

	@NotNull
	@Column(name = "language_level")
	@Min(1)
	@Max(5)
	private Integer languageLevel;

	
}
