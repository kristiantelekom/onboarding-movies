package com.onboarding.movies.model.dto;

import java.util.Date;
import java.util.List;

public class MoviesReadDTO {

    private Integer movieId;
    private String name;
    private String genre;

    String href;
    private Date releaseDate;
    private String language;
    private String director;
    private Integer year;
    private String runtime;
    private List<RolesResponseDTO> roles;
    private String movieImage;
    private String movieImageName;

    public MoviesReadDTO() {
    }

    public MoviesReadDTO(Integer movieId, String name, String genre, String href, Date releaseDate, String language,
                         String director, Integer year, String runtime, List<RolesResponseDTO> roles, String movieImage, String movieImageName) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.href = href;
        this.releaseDate = releaseDate;
        this.language = language;
        this.director = director;
        this.year = year;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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
