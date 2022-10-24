package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.ForeignLanguageService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.ForeignLanguageDao;
import KodlamaIo.hrms.entity.concretes.ForeignLanguage;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {

    private ForeignLanguageDao languageDao;
    private CvIdControl cvIdControl;


    public ForeignLanguageManager(ForeignLanguageDao languageDao, CvIdControl cvIdControl) {
        this.languageDao = languageDao;
        this.cvIdControl = cvIdControl;
    }

    @Override
    public Result add(ForeignLanguage language) {
            this.languageDao.save(language);
            return new SuccessResult("added new language");
    }


    @Override
    public DataResult<List<ForeignLanguage>> getAllForeignLanguages() {
        return new SuccessDataResult<List<ForeignLanguage>>
                (this.languageDao.findAll(), "Foreign language listed");
    }

    @Override
    public DataResult<List<ForeignLanguage>> getByJobSeeker(long id) {
        return new SuccessDataResult<List<ForeignLanguage>>(this.languageDao.getForeignLanguageByLanguageLevel(id), "Dil bilgisi listelendi...");

    }

    @Override
    public DataResult<ForeignLanguage> getForeignLanguageByLanguageName(ForeignLanguage foreignLanguage) {
        Optional<ForeignLanguage> language = languageDao.getForeignLanguageByLanguageNameIgnoreCase(foreignLanguage.getLanguageName());
        if (!language.isPresent()) {
            ForeignLanguage newLanguage = createNewLanguage(foreignLanguage);
            languageDao.save(newLanguage);
            return new SuccessDataResult<>(newLanguage, "data successfully saved");
        }
        return new SuccessDataResult<>(language.get(),"data successfully retrieved");

    }

    private ForeignLanguage createNewLanguage(ForeignLanguage language){
       return ForeignLanguage.builder()
                .languageLevel(language.getLanguageLevel())
                .languageName(language.getLanguageName()).build();
    }
}
