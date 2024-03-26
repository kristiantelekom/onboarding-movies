package com.onboarding.movies.model.dto;

public class GenresDTO {
    private Integer genreId;
    private String genreName;

    public Integer getGenreId() {
        return genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreId(Integer genreId) {
        this.genreId = genreId;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}
