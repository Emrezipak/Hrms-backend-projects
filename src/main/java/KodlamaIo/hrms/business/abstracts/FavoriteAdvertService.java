package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.entity.concretes.FavoriteAdvert;
import KodlamaIo.hrms.payload.request.FavoriteAdvertRequest;

import java.util.List;

public interface FavoriteAdvertService {

    DataResult<List<FavoriteAdvert>> getAllFavoriteAdvert();
    DataResult<FavoriteAdvert> addFavorite(FavoriteAdvertRequest favoriteAdvertRequest);
    DataResult<FavoriteAdvert> deleteFavorite();
    DataResult<List<FavoriteAdvert>> getFavoriteAdvertByJobSeekerEmail(String email);
}
