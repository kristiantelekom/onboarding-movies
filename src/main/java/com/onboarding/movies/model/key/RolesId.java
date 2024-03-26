package com.onboarding.movies.model.key;

import com.onboarding.movies.model.entity.RolesEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
//isto može lombok
//@IdClass(RolesEntity.class)
public class RolesId implements Serializable {

    @Column(name = "movie_id")
    private Integer movieId;
    @Column(name = "actor_id")
    private Integer actorId;

    public RolesId() {
    }

    public RolesId(Integer movieId, Integer actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RolesId rolesId = (RolesId) o;
        return Objects.equals(movieId, rolesId.movieId) &&
                Objects.equals(actorId, rolesId.actorId);
    }

    @Override
    public int hashCode() {
        int result = actorId != null ? actorId.hashCode() : 0;
        result = 31 * result + (movieId != null ? movieId.hashCode() : 0);
        return result;
    }
}
