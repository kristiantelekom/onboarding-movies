package com.onboarding.movies.dao.impl;

import com.onboarding.movies.dao.MoviesDAO;
import com.onboarding.movies.exception.InternalServerErrorException;
import com.onboarding.movies.exception.ResourceNotFoundException;
import com.onboarding.movies.helper.Helper;
import com.onboarding.movies.helper.UtilQueryBuilder;
import com.onboarding.movies.model.entity.MoviesEntity;


import jakarta.transaction.Transactional;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class MoviesDAOImpl implements MoviesDAO {


    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    Helper helper;

    @Autowired
    UtilQueryBuilder utilQueryBuilder;

    private static final Logger logger = LogManager.getLogger(MoviesDAOImpl.class);


    @Override
    public List<MoviesEntity> getMovies() {
        String sql = utilQueryBuilder.getSelectInitialQuery() + utilQueryBuilder.getMoviesTableName();
        try{
            return namedJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(MoviesEntity.class));
        }
        catch (DataAccessException e) {
            //logger.error("DataAccessException exception occurred -> " + helper.convertObjectToString(e));
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public MoviesEntity getMoviesById(Integer movieId) {



        String sql = utilQueryBuilder.getSelectInitialQuery() + utilQueryBuilder.getMoviesTableName()+ " WHERE movie_id = :movieId";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("movieId", movieId);
        try{
            return namedJdbcTemplate.queryForObject(sql, namedParameters,
                    BeanPropertyRowMapper.newInstance(MoviesEntity.class));
        }
        catch (EmptyResultDataAccessException ex) {
            //logger.error("EmptyResultDataAccessException exception occurred -> " + helper.convertObjectToString(ex));
            throw new ResourceNotFoundException("Movie with ID " + movieId + " does not exist");
        } catch (DataAccessException e) {
            //logger.error("DataAccessException exception occurred -> " + helper.convertObjectToString(e));
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public List<MoviesEntity> getMoviesByGenreId(Integer genreId) {

        String sql = utilQueryBuilder.getSelectInitialQuery() + utilQueryBuilder.getMoviesTableName()+ " WHERE genre = :genreId";
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("genreId", genreId);
        try{
            return namedJdbcTemplate.query(sql, namedParameters,BeanPropertyRowMapper.newInstance(MoviesEntity.class));
        }
        catch (EmptyResultDataAccessException ex) {
            //logger.error("EmptyResultDataAccessException exception occurred -> " + helper.convertObjectToString(ex));
            throw new ResourceNotFoundException("Movie with ID " + genreId + " does not exist");
        } catch (DataAccessException e) {
            //logger.error("DataAccessException exception occurred -> " + helper.convertObjectToString(e));
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public MoviesEntity getMoviesByGenre(String genreName) {
        return null;
    }

    @Override
    public MoviesEntity createMovie(MoviesEntity movie) {
        String sql = utilQueryBuilder.insertIntoMovies() +
                " VALUES (:name, :genre, :release_date, :language, :director, :runtime, :description, :year, :movie_image_name, :comment, :rating)";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", movie.getName());
        parameters.put("genre", movie.getGenre());
        parameters.put("release_date", movie.getReleaseDate());
        parameters.put("language", movie.getLanguage());
        parameters.put("director", movie.getDirector());
        parameters.put("runtime", movie.getRuntime());
        parameters.put("description", movie.getDescription());
        parameters.put("year", movie.getYear());
        parameters.put("movie_image_name", movie.getMovieImageName());
        parameters.put("comment", movie.getComment());
        parameters.put("rating", movie.getRating());
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            namedJdbcTemplate.update(sql, namedParameters, keyHolder, new String[]{"movie_id"});
            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                movie.setMovieId(generatedId.intValue());
            }
            return movie;
        } catch (DataAccessException e) {
            //logger.error("DataAccessException exception occurred -> " + helper.convertObjectToString(e));
            throw new InternalServerErrorException(e.getMessage());
        }

    }

    @Override
    public MoviesEntity updateMovie(Integer movieId, MoviesEntity movie) {

        String sql = utilQueryBuilder.alterTable() + utilQueryBuilder.getMoviesTableName()
                + " SET name = :name, genre = :genre, release_date = :release_date, language = :language, director = :director, " +
                "runtime = :runtime, description = :description, year = :year, movie_image_name = :movie_image_name, comment = :comment, rating = :rating"+
                " WHERE movie_id = :movieId";

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", movie.getName());
        parameters.put("genre", movie.getGenre());
        parameters.put("release_date", movie.getReleaseDate());
        parameters.put("language", movie.getLanguage());
        parameters.put("director", movie.getDirector());
        parameters.put("runtime", movie.getRuntime());
        parameters.put("description", movie.getDescription());
        parameters.put("year", movie.getYear());
        parameters.put("movie_image_name", movie.getMovieImageName());
        parameters.put("comment", movie.getComment());
        parameters.put("rating", movie.getRating());
        parameters.put("movieId", movieId);
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(parameters);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            namedJdbcTemplate.update(sql, namedParameters, keyHolder, new String[]{"movie_id"});
            Number generatedId = keyHolder.getKey();
            if (generatedId != null) {
                movie.setMovieId(generatedId.intValue());
            }
            return movie;
        } catch (DataAccessException e) {
            //logger.error("DataAccessException exception occurred -> " + helper.convertObjectToString(e));
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public void deleteMovie(Integer movieId) {
        String sql = utilQueryBuilder.deleteFrom() + utilQueryBuilder.getMoviesTableName() + " WHERE movie_id = :movieId";
        SqlParameterSource namedParameers = new MapSqlParameterSource().addValue("movieId", movieId);
        try {
            namedJdbcTemplate.update(sql, namedParameers);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Movie with movie ID " + movieId + " does not exist");
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
