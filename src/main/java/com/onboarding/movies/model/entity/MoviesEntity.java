package com.onboarding.movies.model.entity;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies", schema = "onboarding_movies")
//ubaci lombok anotacije
public class MoviesEntity {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;
    private String name;
    private Integer genre;
    @Column(name = "release_date")
    private Date releaseDate;
    private String language;
    private String stars;
    private String writers;
    private String director;
    private String runtime;
    private String description;
    private Integer year;
    @Column(name = "movie_image_name")
    private String movieImageName;

    private String comment;

    private Double rating;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "movie_id")
    private List<RolesEntity> roles;


    public MoviesEntity() {
    }

    public MoviesEntity(Integer movieId, String name, Integer genre, Date releaseDate, String language, String stars, String writers, String director, String runtime, String description, Integer year, String movieImageName, String comment, Double rating) {
        this.movieId = movieId;
        this.name = name;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.language = language;
        this.stars = stars;
        this.writers = writers;
        this.director = director;
        this.runtime = runtime;
        this.description = description;
        this.year = year;
        this.movieImageName = movieImageName;
        this.comment = comment;
        this.rating = rating;
    }

    public Integer getMovieId() {
        return movieId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGenre() {
        return genre;
    }

    public void setGenre(Integer genre) {
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

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public String getWriters() {
        return writers;
    }

    public void setWriters(String writers) {
        this.writers = writers;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMovieImageName() {
        return movieImageName;
    }

    public void setMovieImageName(String movieImageName) {
        this.movieImageName = movieImageName;
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


    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public List<RolesEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RolesEntity> roles) {
        this.roles = roles;
    }

    public void addRole(RolesEntity role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
        role.setMovieId(this); // Set the movie reference in the role
    }

    @Override
    public String toString() {
        return "MoviesEntity{" +
                "movieId=" + movieId +
                ", name='" + name + '\'' +
                ", genre=" + genre +
                ", releaseDate=" + releaseDate +
                ", language='" + language + '\'' +
                ", stars='" + stars + '\'' +
                ", writers='" + writers + '\'' +
                ", director='" + director + '\'' +
                ", runtime='" + runtime + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", movieImageName='" + movieImageName + '\'' +
                ", comment='" + comment + '\'' +
                ", rating=" + rating +
                ", roles=" + roles +
                '}';
    }



}
