package KodlamaIo.hrms.controllers;

import KodlamaIo.hrms.business.abstracts.FavoriteAdvertService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/favoriteAdvert")
@CrossOrigin
public class FavoriteAdvertController {

    private FavoriteAdvertService favoriteAdvertService;

    public FavoriteAdvertController(FavoriteAdvertService favoriteAdvertService) {
        this.favoriteAdvertService = favoriteAdvertService;
    }
}
