package KodlamaIo.hrms.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;

import KodlamaIo.hrms.entity.concretes.Image;

import java.util.Optional;

public interface ImageDao extends JpaRepository<Image,Long>{
	Image getByJobSeekerCv_CvId(Long id);
	Optional<Image> getImageByJobSeekerCv_CvId(Long cvId);
}
