package com.onboarding.movies.dao;

import com.onboarding.movies.model.entity.MoviesEntity;

import java.util.List;
import java.util.Optional;

public interface MoviesDAO {

    List<MoviesEntity> getMovies();
    Optional<MoviesEntity> getMoviesById(Integer movieId);
    MoviesEntity getMoviesByName(String movieName);
    MoviesEntity createMovie(MoviesEntity movie);
    MoviesEntity updateMovie(Integer movieId, MoviesEntity movie);
    void deleteMovie(Integer movieId);

}
