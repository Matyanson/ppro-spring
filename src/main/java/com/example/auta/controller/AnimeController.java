package com.example.auta.controller;

import com.example.auta.model.Anime;
import com.example.auta.service.AnimeService;
import com.example.auta.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    private final AnimeService animeService;
    private final GenreService genreService;

    public AnimeController(AnimeService animeService, GenreService genreService) {
        this.animeService = animeService;
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("animeList", animeService.getAllAnime());
        return "anime_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {
        Anime anime = animeService.getAnimeById(id);
        if (anime == null) return "redirect:/anime/";

        model.addAttribute("anime", anime);
        return "anime_detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("anime", new Anime());
        model.addAttribute("genres", genreService.getAllGenres());
        return "anime_form";
    }

    @PostMapping("/save")
    public String save(@Valid Anime anime, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genres", genreService.getAllGenres());
            return "anime_form";
        }
        animeService.saveAnime(anime);
        return "redirect:/anime/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        Anime anime = animeService.getAnimeById(id);
        if (anime == null) return "redirect:/anime/";

        model.addAttribute("anime", anime);
        model.addAttribute("genres", genreService.getAllGenres());
        return "anime_form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        animeService.deleteAnime(id);
        return "redirect:/anime/";
    }
}
