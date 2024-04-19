package com.onboarding.movies.controller;

import com.onboarding.movies.helper.Helper;
import com.onboarding.movies.model.dto.MoviesCreationalDTO;
import com.onboarding.movies.model.dto.MoviesCreationalResponseDTO;
import com.onboarding.movies.model.dto.MoviesReadDTO;
import com.onboarding.movies.service.MoviesService;


import jakarta.ws.rs.QueryParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("movies")
@CrossOrigin
public class MoviesController {


    @Autowired
    MoviesService moviesService;

    @Autowired
    Helper helper;

    private static final Logger logger = LogManager.getLogger(MoviesController.class);


    @GetMapping()
    public ResponseEntity<List<MoviesReadDTO>> getMoviebyGenreId(@RequestParam(value = "genre", required = false) Integer genreId){
        if(genreId != null){
            List<MoviesReadDTO> readMovies = moviesService.getMoviebyGenreId(genreId);
            return new ResponseEntity<>(readMovies, HttpStatus.OK);
        }
        logger.info("Method getMoviebyId started! ");
        List<MoviesReadDTO> readMovie = moviesService.getAllMovies();

        //readMovie.setHref(WebMvcLinkBuilder.linkTo(methodOn(MoviesController.class).getMoviebyId(movieId)).toUriComponentsBuilder().toUriString());
        logger.info("API response - getMoviebyId -> " + helper.convertObjectToString(readMovie));

        logger.info("Method getMoviebyId finished! ");
        return new ResponseEntity<>(readMovie, HttpStatus.OK);
    }
    
//    @GetMapping()
//    public ResponseEntity<List<MoviesReadDTO>> getAllMovies(){
//        logger.info("Method getMoviebyId started! ");
//        List<MoviesReadDTO> readMovie = moviesService.getAllMovies();
//
//        //readMovie.setHref(WebMvcLinkBuilder.linkTo(methodOn(MoviesController.class).getMoviebyId(movieId)).toUriComponentsBuilder().toUriString());
//        logger.info("API response - getMoviebyId -> " + helper.convertObjectToString(readMovie));
//
//        logger.info("Method getMoviebyId finished! ");
//        return new ResponseEntity<>(readMovie, HttpStatus.OK);
//    }

    @GetMapping(value = "/{movieId:[0-9]+}")
    public ResponseEntity<MoviesReadDTO> getMoviebyId(@PathVariable Integer movieId){
        logger.info("Method getMoviebyId started! ");
        MoviesReadDTO readMovie = moviesService.getMoviebyId(movieId);

        readMovie.setHref(WebMvcLinkBuilder.linkTo(methodOn(MoviesController.class).getMoviebyId(movieId)).toUriComponentsBuilder().toUriString());
        logger.info("API response - getMoviebyId -> " + helper.convertObjectToString(readMovie));

        logger.info("Method getMoviebyId finished! ");
        return new ResponseEntity<>(readMovie, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MoviesCreationalResponseDTO> createMovie(@RequestBody MoviesCreationalDTO moviesCreationalDTO){
        logger.info("Method createMovie started! ");
        MoviesCreationalResponseDTO createdMovie = moviesService.createMovies(moviesCreationalDTO);
        createdMovie.setHref(WebMvcLinkBuilder.linkTo(methodOn(MoviesController.class).getMoviebyId(createdMovie.getMovieId())).toUriComponentsBuilder().toUriString());
        logger.info("API response - getMoviebyId -> " + helper.convertObjectToString(createdMovie));
        logger.info("Method createMovie finished! ");
        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{movieId:[0-9]+}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Integer movieId){
        logger.info("Method deleteMovie started! ");
        moviesService.deleteMovie(movieId);
        logger.info("Method deleteMovie finished! ");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(value = "/{movieId:[0-9]+}")
    public ResponseEntity<MoviesCreationalResponseDTO> updateMovie(@RequestBody MoviesCreationalDTO moviesCreationalDTO, @PathVariable Integer movieId){
        logger.info("Method updateMovie started! ");
        MoviesCreationalResponseDTO updatedMovie = moviesService.updateMovie(moviesCreationalDTO, movieId);
        updatedMovie.setHref(WebMvcLinkBuilder.linkTo(methodOn(MoviesController.class).getMoviebyId(movieId)).toUriComponentsBuilder().toUriString());
        logger.info("API response - updateMovie -> " + helper.convertObjectToString(updatedMovie));
        logger.info("Method createMovie finished! ");
        return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
    }
}
