package com.example.auta.repository;

import com.example.auta.model.Anime;
import com.example.auta.model.Rating;
import com.example.auta.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByAnimeId(Long animeId);
    List<Rating> findByUserId(Long userId);
    Rating findByAnimeAndUser(Anime anime, User user);
    @Query("SELECT AVG(r.score) FROM Rating r WHERE r.anime = :anime")
    Double findAverageRatingByAnime(@Param("anime") Anime anime);
}

