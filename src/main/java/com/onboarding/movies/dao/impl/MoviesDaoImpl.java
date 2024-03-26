package com.onboarding.movies.dao.impl;

import com.onboarding.movies.dao.MoviesDAO;
import com.onboarding.movies.model.entity.MoviesEntity;


import jakarta.persistence.*;
import jakarta.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class MoviesDaoImpl implements MoviesDAO {


    private EntityManager entityManager;

    public MoviesDaoImpl() {
    }

    @Autowired
    public MoviesDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MoviesEntity> getMovies() {
        return null;
    }

    @Override
    public Optional<MoviesEntity> getMoviesById(Integer movieId) {
        return null;
    }

    @Override
    public MoviesEntity getMoviesByName(String movieName) {
        return null;
    }

    @Override
    public MoviesEntity createMovie(MoviesEntity movie) {

        entityManager.persist(movie);


        return entityManager.find(MoviesEntity.class, movie.getMovieId());
    }

    @Override
    public MoviesEntity updateMovie(Integer movieId, MoviesEntity movie) {
        return null;
    }

    @Override
    public void deleteMovie(Integer movieId) {

    }
}
