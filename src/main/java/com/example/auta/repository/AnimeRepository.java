package com.example.auta.repository;

import com.example.auta.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByTitleContaining(String title);
    List<Anime> findByGenreId(Long genreId);
}
