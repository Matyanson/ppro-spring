package com.example.auta.controller;

import com.example.auta.model.Watchlist;
import com.example.auta.service.AnimeService;
import com.example.auta.service.WatchlistService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/watchlists")
public class WatchlistController {

    private final WatchlistService watchlistService;
    private final AnimeService animeService;

    public WatchlistController(WatchlistService watchlistService, AnimeService animeService) {
        this.watchlistService = watchlistService;
        this.animeService = animeService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("watchlists", watchlistService.getAllWatchlists());
        return "watchlist/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("watchlist", new Watchlist());
        model.addAttribute("animeList", animeService.getAllAnime());
        return "watchlist/edit";
    }

    @PostMapping("/save")
    public String save(@Valid Watchlist watchlist, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("animeList", animeService.getAllAnime());
            return "watchlist/edit";
        }
        watchlistService.saveWatchlist(watchlist);
        return "redirect:/watchlists/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        watchlistService.deleteWatchlist(id);
        return "redirect:/watchlists/";
    }
}
