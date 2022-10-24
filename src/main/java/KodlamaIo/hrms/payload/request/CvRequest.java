package KodlamaIo.hrms.payload.request;

import KodlamaIo.hrms.entity.concretes.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CvRequest {


    private String linkedLnAddress;

    private String githubAddress;

    private Set<String> technologies;

    private Set<ForeignLanguage> foreignLanguages;

    private Set<ExperienceCreateRequest> experience;

    private Set<SchoolCreateRequest> schools;
}
