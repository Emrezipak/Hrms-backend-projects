package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.ForeignLanguage;

import java.util.List;

public interface ForeignLanguageService{

    Result add(ForeignLanguage languageName);

    DataResult<List<ForeignLanguage>> getAllForeignLanguages();

    DataResult<List<ForeignLanguage>> getByJobSeeker(long id);
    DataResult<ForeignLanguage> getForeignLanguageByLanguageName(ForeignLanguage languageName);

}
