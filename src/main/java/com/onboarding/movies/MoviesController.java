package com.onboarding.movies;

import com.onboarding.movies.model.dto.MoviesCreationalDTO;
import com.onboarding.movies.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MoviesController {


    @Autowired
    MoviesService moviesService;

    @PostMapping
    public void createMovie(@RequestBody MoviesCreationalDTO moviesCreationalDTO){
        moviesService.createMovies(moviesCreationalDTO);
    }
}
