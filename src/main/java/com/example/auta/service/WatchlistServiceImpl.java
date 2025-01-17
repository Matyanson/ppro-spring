package com.example.auta.service;

import com.example.auta.model.Anime;
import com.example.auta.model.User;
import com.example.auta.model.Watchlist;
import com.example.auta.repository.WatchlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    private final WatchlistRepository watchlistRepository;

    public WatchlistServiceImpl(WatchlistRepository watchlistRepository) {
        this.watchlistRepository = watchlistRepository;
    }

    @Override
    public List<Watchlist> getAllWatchlists() {
        return watchlistRepository.findAll();
    }

    @Override
    public List<Watchlist> getAllWatchlistsByUser(User user) {
        return watchlistRepository.findByUser(user);
    }

    @Override
    public void addAnimeToWatchlist(Watchlist watchlist, Anime anime) {
        if (!watchlist.getAnimeList().contains(anime)) {
            watchlist.getAnimeList().add(anime);
            watchlistRepository.save(watchlist);
        }
    }

    @Override
    public void saveWatchlist(Watchlist watchlist, User user) {
        watchlist.setUser(user);
        watchlistRepository.save(watchlist);
    }

    @Override
    public void deleteWatchlist(long id) {
        watchlistRepository.deleteById(id);
    }

    @Override
    public Watchlist getWatchlistById(long id) {
        return watchlistRepository.findById(id);
    }
}


