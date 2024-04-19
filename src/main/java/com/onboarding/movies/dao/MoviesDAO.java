package com.onboarding.movies.dao;

import com.onboarding.movies.model.entity.MoviesEntity;

import java.util.List;
import java.util.Optional;

public interface MoviesDAO {

    List<MoviesEntity> getMovies();
    MoviesEntity getMoviesById(Integer movieId);
    MoviesEntity getMoviesByGenre(String genreName);
    MoviesEntity createMovie(MoviesEntity movie);
    MoviesEntity updateMovie(Integer movieId, MoviesEntity movie);
    List<MoviesEntity> getMoviesByGenreId(Integer genreId);
    void deleteMovie(Integer movieId);

}
