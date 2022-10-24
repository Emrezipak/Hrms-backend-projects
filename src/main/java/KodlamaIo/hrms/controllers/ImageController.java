package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.Image;
import KodlamaIo.hrms.entity.concretes.JobSeeker;

@RestController
@RequestMapping(value="api/image")
@CrossOrigin
public class ImageController {
	
	private ImageService imageService;
	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}
    
    @PostMapping("/addImageToCv")
	public Result add(@RequestParam(name = "cvId") Long id,
					  @RequestParam(name = "image") MultipartFile file) {
    	return this.imageService.addImage(id,file);
    }
    
    @DeleteMapping("/deleteImage")
	public Result deleteImage(@RequestParam(name="imageId") Long id) {

    	return this.imageService.deleteImageById(id);
    }
    
    @GetMapping("/getImageById")
	public Result getByJobSeeker_id(@RequestParam(name = "imageId") Long id) {
    	return this.imageService.getByJobSeeker_id(id);
    }

}
