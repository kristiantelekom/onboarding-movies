package com.onboarding.movies.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class MoviesCreationalResponseDTO {
    private Integer movieId;
    private String name;
    @JsonProperty("genre")
    private String genre;

    String href;
    private Date releaseDate;
    private String comment;
    private Double rating;
    private String language;
    private String description;
    private String director;
    private String runtime;
    private List<RolesResponseDTO> roles;
    private String movieImage;
    private String movieImageName;

    public MoviesCreationalResponseDTO() {
    }

    public MoviesCreationalResponseDTO(Integer movieId, String name, String genre, String href, Date releaseDate, String comment, Double rating,
                                       String language, String description, String director, String runtime, List<RolesResponseDTO> roles, String movieImage, String movieImageName) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.href = href;
        this.releaseDate = releaseDate;
        this.comment = comment;
        this.rating = rating;
        this.language = language;
        this.description = description;
        this.director = director;
        this.runtime = runtime;
        this.roles = roles;
        this.movieImage = movieImage;
        this.movieImageName = movieImageName;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public List<RolesResponseDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesResponseDTO> roles) {
        this.roles = roles;
    }

    public String getMovieImage() {
        return movieImage;
    }

    public void setMovieImage(String movieImage) {
        this.movieImage = movieImage;
    }

    public String getMovieImageName() {
        return movieImageName;
    }

    public void setMovieImageName(String movieImageName) {
        this.movieImageName = movieImageName;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
