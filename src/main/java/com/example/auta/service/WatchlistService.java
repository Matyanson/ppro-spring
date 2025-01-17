package com.example.auta.service;

import com.example.auta.model.Watchlist;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WatchlistService {
    List<Watchlist> getAllWatchlists();
    void saveWatchlist(Watchlist watchlist);
    void deleteWatchlist(long id);
    Watchlist getWatchlistById(long id);
}