package com.example.auta.service;

import com.example.auta.model.Anime;
import com.example.auta.repository.AnimeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;

    public WatchlistServiceImpl(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @Override
    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    @Override
    public Anime getAnimeById(long id) {
        return animeRepository.findById(id).orElse(null);
    }

    @Override
    public void saveAnime(Anime anime) {
        animeRepository.save(anime);
    }

    @Override
    public void deleteAnime(long id) {
        animeRepository.deleteById(id);
    }
}

