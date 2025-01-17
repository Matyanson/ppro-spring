package com.example.auta.service;

import com.example.auta.model.Anime;
import com.example.auta.model.Rating;
import com.example.auta.model.User;
import com.example.auta.repository.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(long id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating getRatingByAnimeAndUser(Anime anime, User user) {
        return ratingRepository.findByAnimeAndUser(anime, user);
    }
}


