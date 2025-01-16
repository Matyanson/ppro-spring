package com.example.auta.repository;

import com.example.auta.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByAnimeId(Long animeId);
    List<Rating> findByUserId(Long userId);
}

