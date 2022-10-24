package KodlamaIo.hrms.business.abstracts;

import KodlamaIo.hrms.core.utilities.results.DataResult;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.entity.concretes.FavoriteAdvert;
import KodlamaIo.hrms.payload.request.FavoriteAdvertRequest;
import KodlamaIo.hrms.payload.response.FavoriteAdvertResponse;

import java.util.List;

public interface FavoriteAdvertService {

    DataResult<List<FavoriteAdvert>> getAllFavoriteAdvert();
    DataResult<FavoriteAdvert> addFavorite(FavoriteAdvertRequest favoriteAdvertRequest);
    Result deleteFavorite(Long id);
    DataResult<List<FavoriteAdvertResponse>> getFavoriteAdvertByJobSeekerEmail(String email);
}
