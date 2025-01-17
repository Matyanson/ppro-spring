package com.example.auta.controller;

import com.example.auta.model.Anime;
import com.example.auta.service.AnimeService;
import com.example.auta.service.GenreService;
import com.example.auta.service.RatingService;
import com.example.auta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class IndexController {

    private final AnimeService animeService;
    private final RatingService ratingService;

    public IndexController(AnimeService animeService, RatingService ratingService) {
        this.animeService = animeService;
        this.ratingService = ratingService;
    }

    @GetMapping({"/", "/test"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/admin/")
    @ResponseBody
    public String admin(){
        return "<h1>Admin section</h1>";
    }
    @GetMapping("/403")
    @ResponseBody
    public String forbidden(){
        return "<h1>Access Denied</h1>";
    }
}

