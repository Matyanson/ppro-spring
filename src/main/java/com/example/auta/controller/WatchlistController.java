package com.example.auta.controller;

import com.example.auta.model.Anime;
import com.example.auta.model.User;
import com.example.auta.model.Watchlist;
import com.example.auta.service.AnimeService;
import com.example.auta.service.UserService;
import com.example.auta.service.WatchlistService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;
    private final AnimeService animeService;
    private final UserService userService;

    public WatchlistController(WatchlistService watchlistService, AnimeService animeService, UserService userService) {
        this.watchlistService = watchlistService;
        this.animeService = animeService;
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("watchlists", watchlistService.getAllWatchlistsByUser(user));
        return "watchlist/list";
    }

    @GetMapping("/detail/{id}")
    public String list(Model model, @PathVariable long id) {
        Watchlist list = watchlistService.getWatchlistById(id);
        List<Anime> animeList = list.getAnimeList();
        model.addAttribute("watchlist", list);
        model.addAttribute("animeList", animeList);
        return "watchlist/detail";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("watchlist", new Watchlist());
        model.addAttribute("edit", false);
        return "watchlist/edit";
    }

    @PostMapping("/save")
    public String save(@Valid Watchlist watchlist, BindingResult bindingResult, Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("watchlist", new Watchlist());
            model.addAttribute("edit", false);
            return "watchlist/edit";
        }
        watchlistService.saveWatchlist(watchlist, user);
        return "redirect:/watchlist";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        watchlistService.deleteWatchlist(id);
        return "redirect:/watchlist";
    }
}
