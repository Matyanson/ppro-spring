package com.example.auta.service;

import com.example.auta.model.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    List<Genre> getAllGenres();
    void saveGenre(Genre genre);
    void deleteGenre(long id);
    boolean existsByName(String name);
}
