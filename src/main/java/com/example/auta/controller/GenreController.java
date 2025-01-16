package com.example.auta.controller;

import com.example.auta.model.Genre;
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
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "genre_list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre_form";
    }

    @PostMapping("/save")
    public String save(@Valid Genre genre, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "genre_form";
        }
        genreService.saveGenre(genre);
        return "redirect:/genres/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres/";
    }
}