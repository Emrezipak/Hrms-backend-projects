package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService{

    DataResult<Image> addImage(Long id,MultipartFile file);
    
	Result deleteImageById(Long imageId);
	
	Result getByJobSeeker_id(Long id);



}
