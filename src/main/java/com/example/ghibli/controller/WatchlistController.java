package com.example.ghibli.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ghibli.model.WatchlistMovie;
import com.example.ghibli.repository.WatchlistRepository;

@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistRepository repository;

    public WatchlistController(WatchlistRepository repository) {
        this.repository = repository;
    }

    // ✅ CREATE – Add movie to watchlist
    @PostMapping
    public String addToWatchlist(@RequestBody WatchlistMovie movie,
                                 Authentication authentication) {

        String userEmail = authentication.getName();

        if (repository.existsByMovieIdAndUserEmail(movie.getMovieId(), userEmail)) {
            return "Movie already in watchlist";
        }

        movie.setUserEmail(userEmail);
        movie.setAddedAt(LocalDateTime.now());

        repository.save(movie);
        return "Movie added to watchlist";
    }

    // ✅ READ – Get logged-in user's watchlist
    @GetMapping
    public List<WatchlistMovie> getMyWatchlist(Authentication authentication) {
        String userEmail = authentication.getName();
        return repository.findByUserEmail(userEmail);
    }

    // ✅ DELETE – Remove movie from watchlist
    @DeleteMapping("/{movieId}")
    public String removeFromWatchlist(@PathVariable String movieId,
                                      Authentication authentication) {

        String userEmail = authentication.getName();
        repository.deleteByMovieIdAndUserEmail(movieId, userEmail);
        return "Movie removed from watchlist";
    }
}
