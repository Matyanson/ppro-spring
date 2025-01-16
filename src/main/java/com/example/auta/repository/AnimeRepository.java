package com.example.auta.repository;

import com.example.auta.model.Anime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<Anime, Long> {
    List<Anime> findByTitleContaining(String title);
    @Query("SELECT a FROM Anime a JOIN a.genres g WHERE g.id = :genreId")
    List<Anime> findByGenreId(@Param("genreId") Long genreId);
}
