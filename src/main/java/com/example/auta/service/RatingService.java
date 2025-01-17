package com.example.auta.service;

import com.example.auta.model.Anime;
import com.example.auta.model.Rating;
import com.example.auta.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    List<Rating> getAllRatings();
    void saveRating(Rating rating);
    void deleteRating(long id);
    Rating getRatingByAnimeAndUser(Anime anime, User user);
}
