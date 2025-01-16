package com.example.auta.service;

import com.example.auta.model.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    List<Rating> getAllRatings();
    void saveRating(Rating rating);
    void deleteRating(long id);
}
