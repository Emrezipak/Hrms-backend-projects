package KodlamaIo.hrms.dataAccess;

import KodlamaIo.hrms.entity.concretes.FavoriteAdvert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteAdvertDao extends JpaRepository<FavoriteAdvert,Long> {

    List<FavoriteAdvert> getFavoriteAdvertByJobSeeker_Email(String email);
}
