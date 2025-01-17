package com.example.auta.controller;

import com.example.auta.model.Anime;
import com.example.auta.model.Rating;
import com.example.auta.model.User;
import com.example.auta.service.AnimeService;
import com.example.auta.service.GenreService;
import com.example.auta.service.RatingService;
import com.example.auta.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    private final AnimeService animeService;
    private final GenreService genreService;
    private final UserService userService;
    private final RatingService ratingService;

    public AnimeController(AnimeService animeService, GenreService genreService, UserService userService, RatingService ratingService) {
        this.animeService = animeService;
        this.genreService = genreService;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("animeList", animeService.getAllAnime());
        return "anime/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(
            Model model,
            @PathVariable long id,
            Principal principal
    ) {
        Anime anime = animeService.getAnimeById(id);
        User user = userService.findByUsername(principal.getName());
        Rating userRating = ratingService.getRatingByAnimeAndUser(anime, user);
        Double averageRating = animeService.getAnimeAverageRating(anime);
        if (anime == null) return "redirect:/anime/";

        // Handle case where there are no ratings yet
        if (averageRating == null) {
            averageRating = 0.0;
        }

        model.addAttribute("anime", anime);
        model.addAttribute("averageRating", averageRating);
        model.addAttribute("userRating", userRating != null ? userRating.getScore() : 0);
        return "anime/detail";
    }

    @PostMapping("/rate/{id}")
    public String rateAnime(@PathVariable Long id, @RequestParam("score") int score, Principal principal, RedirectAttributes redirectAttributes) {
        Anime anime = animeService.getAnimeById(id);
        User user = userService.findByUsername(principal.getName());

        // Check if the user has already rated this anime
        Rating existingRating = ratingService.getRatingByAnimeAndUser(anime, user);
        if (existingRating != null) {
            // Update the existing rating
            existingRating.setScore(score);
            ratingService.saveRating(existingRating);
        } else {
            // Create a new rating
            Rating newRating = new Rating();
            newRating.setScore(score);
            newRating.setAnime(anime);
            newRating.setUser(user);
            ratingService.saveRating(newRating);
        }

        redirectAttributes.addFlashAttribute("message", "Your rating has been submitted!");
        return "redirect:/anime/detail/{id}";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("anime", new Anime());
        model.addAttribute("edit", false);
        model.addAttribute("genres", genreService.getAllGenres());
        return "anime/edit";
    }

    @PostMapping("/save")
    public String save(@Valid Anime anime, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("anime", anime);
            model.addAttribute("genres", genreService.getAllGenres());
            return "anime/detail";
        }
        animeService.saveAnime(anime);
        return "redirect:/anime";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Anime anime = animeService.getAnimeById(id);
        if (anime == null) return "redirect:/anime";

        model.addAttribute("anime", anime);
        model.addAttribute("edit", true);
        model.addAttribute("genres", genreService.getAllGenres());
        return "anime/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        animeService.deleteAnime(id);
        return "redirect:/anime";
    }
}
