package com.onboarding.movies.helper;

import org.springframework.stereotype.Component;

@Component
public class UtilQueryBuilder {
    private static final String moviesTableName = "onboarding_movies.movies";
    private static final String rolesTableName = "onboarding_movies.roles";

    public String getMoviesTableName(){
        return moviesTableName;
    }

    public String getRolesTableName(){
        return rolesTableName;
    }

    public String getSelectInitialQuery(){
        return "SELECT * FROM ";
    }

    public String deleteFrom(){
        return "DELETE FROM ";
    }

    public String alterTable(){
        return "UPDATE ";
    }

    public String insertIntoMovies() {
        return "INSERT INTO " + moviesTableName+"(name, genre, release_date, language, director, runtime, description, year, movie_image_name, comment, rating)";
    }

    public String insertIntoRoles() {
        return "INSERT INTO " + rolesTableName + "(movie_id, actor_id, role_name)";
    }
}
