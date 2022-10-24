package KodlamaIo.hrms.business.imp;

import KodlamaIo.hrms.business.abstracts.ImageService;

import KodlamaIo.hrms.core.cloudinary.ImageCheckService;
import KodlamaIo.hrms.core.utilities.results.*;
import KodlamaIo.hrms.dataAccess.ImageDao;
import KodlamaIo.hrms.entity.concretes.Image;
import KodlamaIo.hrms.entity.concretes.JobSeekerCv;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.Optional;

@Service
public class ImageManager implements ImageService {

	private ImageDao imageDao;
	private ImageCheckService imageCheckService;
	private CvIdControl cvIdControl;
	public ImageManager(ImageDao imageDao,
						ImageCheckService imageCheckService,
						CvIdControl cvIdControl) {
		this.imageDao = imageDao;
		this.imageCheckService = imageCheckService;
		this.cvIdControl = cvIdControl;
	}


	@Override
	public DataResult<Image> addImage(Long id,MultipartFile file) {
		DataResult<JobSeekerCv> jobSeekerCv = this.cvIdControl.getByJobSeekerCvId(id);
		if(jobSeekerCv.isSuccess()){
			Image imageDataResult =  getImageByCvId(id);
			Map<String,String> photo = (Map<String,String>) this.imageCheckService.loadFile(file)
					.getData();

			String url = photo.get("url");
			Image image = Image.builder().imageUrl(url)
						.jobSeekerCv(jobSeekerCv.getData())
						.id(imageDataResult != null  ? imageDataResult.getId() : null)
						.build();
			this.imageDao.save(image);
			return new SuccessDataResult(image,"added new image");
		}
		return new ErrorDataResult(jobSeekerCv.getMessage());

	}

	@Override
	public Result deleteImageById(Long imageId) {
		Optional<Image> image = imageDao.findById(imageId);
		if (image.isPresent()){
			this.imageDao.deleteById(imageId);
			imageDao.flush();
			return new SuccessResult("deleted image");
		}
		return new ErrorResult("not found image");
	}

	@Override
	public Result getByJobSeeker_id(Long id) {
		Optional<Image> image = imageDao.findById(id);
		if (image.isPresent()){
			return new SuccessDataResult(image.get().getImageUrl(),"success");
		}
		return new ErrorResult("not found image");

	}
	private Image getImageByCvId(Long cvId){
		Optional<Image> image = imageDao.getImageByJobSeekerCv_CvId(cvId);
		if(image.isPresent()){
			return image.get();
		}
		return null;
	}
}
