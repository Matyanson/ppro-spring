package com.example.auta.controller;

import com.example.auta.model.Rating;
import com.example.auta.service.AnimeService;
import com.example.auta.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ratings")
public class RatingController {

    private final RatingService ratingService;
    private final AnimeService animeService;

    public RatingController(RatingService ratingService, AnimeService animeService) {
        this.ratingService = ratingService;
        this.animeService = animeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("ratings", ratingService.getAllRatings());
        return "rating/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("rating", new Rating());
        model.addAttribute("animeList", animeService.getAllAnime());
        return "rating/edit";
    }

    @PostMapping("/save")
    public String save(@Valid Rating rating, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("animeList", animeService.getAllAnime());
            return "rating/edit";
        }
        ratingService.saveRating(rating);
        return "redirect:/ratings/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        ratingService.deleteRating(id);
        return "redirect:/ratings/";
    }
}