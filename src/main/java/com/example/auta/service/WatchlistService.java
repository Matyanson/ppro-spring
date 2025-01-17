package com.example.auta.service;

import com.example.auta.model.Anime;
import com.example.auta.model.User;
import com.example.auta.model.Watchlist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchlistService {
    List<Watchlist> getAllWatchlists();
    List<Watchlist> getAllWatchlistsByUser(User user);
    void addAnimeToWatchlist(Watchlist watchlist, Anime anime);
    void saveWatchlist(Watchlist watchlist, User user);
    void deleteWatchlist(long id);
    Watchlist getWatchlistById(long id);
}