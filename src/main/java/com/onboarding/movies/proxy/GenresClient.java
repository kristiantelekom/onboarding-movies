package com.onboarding.movies.proxy;

import com.onboarding.movies.model.dto.GenresDTO;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "onboarding-genres", url = "http://localhost:8091", path = "/genres")
public interface GenresClient {

    @GetMapping(value = "/{genreId:[0-9]+}")
    ResponseEntity<GenresDTO> getGenreById(@PathVariable Integer genreId);

    @GetMapping(value = "/{genreName:[A-Za-z]+}")
    ResponseEntity<GenresDTO> getGenreByName(@PathVariable String genreName);

}
