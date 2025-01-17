package com.example.auta.service;

import com.example.auta.model.Anime;
import com.example.auta.repository.AnimeRepository;
import com.example.auta.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;
    private final RatingRepository ratingRepository;

    public AnimeServiceImpl(AnimeRepository animeRepository, RatingRepository ratingRepository) {
        this.animeRepository = animeRepository;
        this.ratingRepository = ratingRepository;
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
    public Double getAnimeAverageRating(Anime anime) {
        return ratingRepository.findAverageRatingByAnime(anime);
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

