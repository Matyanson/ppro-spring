package com.example.auta.service;

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
    public void saveWatchlist(Watchlist watchlist) {
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


