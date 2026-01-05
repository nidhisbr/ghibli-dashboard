package com.example.ghibli.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.ghibli.model.WatchlistMovie;

public interface WatchlistRepository extends MongoRepository<WatchlistMovie, String> {

    // READ → get all movies for logged-in user
    List<WatchlistMovie> findByUserEmail(String userEmail);

    // DELETE → remove a movie from user's watchlist
    void deleteByMovieIdAndUserEmail(String movieId, String userEmail);

    // CHECK → avoid duplicate watchlist entry
    boolean existsByMovieIdAndUserEmail(String movieId, String userEmail);
}
