package KodlamaIo.hrms.dataAccess;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.ForeignLanguage;

public interface ForeignLanguageDao extends JpaRepository<ForeignLanguage,Integer>{
	
	List<ForeignLanguage> getForeignLanguageByLanguageLevel(Long id);
	boolean getAllByLanguageName(String language);

	Optional<ForeignLanguage> getForeignLanguageByLanguageNameIgnoreCase(String languageName);

}
