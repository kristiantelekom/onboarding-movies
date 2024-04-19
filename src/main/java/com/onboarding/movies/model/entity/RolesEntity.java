package com.onboarding.movies.model.entity;

import com.onboarding.movies.model.dto.RolesDTO;
import com.onboarding.movies.model.key.RolesId;
import jakarta.persistence.*;
import org.hibernate.annotations.Columns;

@Entity
@Table(name = "roles", schema = "onboarding_movies")
@IdClass(RolesId.class)
public class RolesEntity {

    //@Id
//    @EmbeddedId
//    RolesId rolesId = new RolesId();

    @Id
    private Integer movieId;

    @Id
    private Integer actorId;

    @Column(name = "role_name")
    private String roleName;


    public RolesEntity() {
    }

    public RolesEntity(Integer movieId, Integer actorId, String roleName) {
        this.movieId = movieId;
        this.actorId = actorId;
        this.roleName = roleName;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
