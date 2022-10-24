package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.FavoriteAdvertService;
import KodlamaIo.hrms.core.utilities.results.Result;
import KodlamaIo.hrms.payload.request.FavoriteAdvertRequest;
import KodlamaIo.hrms.payload.response.FavoriteAdvertResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/favoriteAdvert")
@CrossOrigin
public class FavoriteAdvertController {

    private FavoriteAdvertService favoriteAdvertService;

    public FavoriteAdvertController(FavoriteAdvertService favoriteAdvertService) {
        this.favoriteAdvertService = favoriteAdvertService;
    }

    @PostMapping("/addFavorite")
    public Result addFavorite(@RequestBody @Valid FavoriteAdvertRequest favoriteAdvertRequest) {
        return favoriteAdvertService.addFavorite(favoriteAdvertRequest);
    }

    @GetMapping("/getAvdert_favorite_ByEmail")
    public Result getAdvertFavoritesByEmail(@RequestParam(name = "email") String email) {
        return favoriteAdvertService.getFavoriteAdvertByJobSeekerEmail(email);
    }

    @DeleteMapping("/delete_Advert_Favorite/{id}")
    public Result deleteAdvertFavoriteById(@PathVariable(name = "id") Long id) {
        return favoriteAdvertService.deleteFavorite(id);
    }

    @GetMapping("/getAllFavoriteAdvert")
    public Result getAllFavoriteAdvert() {
        return favoriteAdvertService.getAllFavoriteAdvert();
    }
}
