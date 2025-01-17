package com.example.auta.service;

import com.example.auta.model.Anime;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AnimeService {
    List<Anime> getAllAnime();
    Anime getAnimeById(long id);
    Double getAnimeAverageRating(Anime anime);
    void saveAnime(Anime anime);
    void deleteAnime(long id);
}