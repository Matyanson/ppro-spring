package com.example.auta.controller;

import com.example.auta.model.Anime;
import com.example.auta.model.Genre;
import com.example.auta.service.GenreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("genres", genreService.getAllGenres());
        return "genres/list";
    }

    // Show the form to create a new genre
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("genre", new Genre()); // Empty genre object for creation
        model.addAttribute("edit", false); // Flag for creation
        return "genres/edit"; // Return to the edit form
    }

   // Handle save request for a new genre
    @PostMapping("/save")
    public String save(@Valid Genre genre, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Validate if the genre name already exists
        if (genreService.existsByName(genre.getName())) {
            bindingResult.rejectValue("name", "error.genre", "This genre name already exists.");
        }

        // If there are validation errors, stay on the form
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("genre", genre);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.genre", bindingResult);
            redirectAttributes.addFlashAttribute("edit", false); // Indicate it's a new genre creation
            return "redirect:/genres/create";
        }

        // Save the genre if there are no errors
        genreService.saveGenre(genre);
        return "redirect:/genres"; // Redirect to the list of genres
    }

    // Handle update request for an existing genre
    @PostMapping("/update")
    public String update(@Valid Genre genre, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // Validate if the genre name already exists
        if (genreService.existsByName(genre.getName())) {
            bindingResult.rejectValue("name", "error.genre", "This genre name already exists.");
        }

        // If there are validation errors, stay on the form
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("genre", genre);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.genre", bindingResult);
            redirectAttributes.addFlashAttribute("edit", true); // Indicate it's an edit operation
            return "redirect:/genres/edit/" + genre.getId();
        }

        // Update the genre if there are no errors
        genreService.saveGenre(genre);
        return "redirect:/genres"; // Redirect to the list of genres
    }

    // Delete a genre by ID
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        genreService.deleteGenre(id);
        return "redirect:/genres"; // Redirect to the genres list after deletion
    }
}