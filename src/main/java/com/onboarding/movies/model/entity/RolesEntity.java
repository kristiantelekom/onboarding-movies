package com.onboarding.movies.model.entity;

import com.onboarding.movies.model.dto.RolesDTO;
import com.onboarding.movies.model.key.RolesId;
import jakarta.persistence.*;

@Entity
@Table(name = "roles", schema = "onboarding_movies")
@IdClass(RolesId.class)
public class RolesEntity {



    @Id
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private MoviesEntity movieId;

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private ActorReference actorId;


    @Column(name = "role_name")
    private String roleName;



    public RolesEntity() {
    }

    public RolesEntity(ActorReference actorId, String roleName, MoviesEntity movieId) {
        this.actorId = actorId;
        this.roleName = roleName;
        this.movieId = movieId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public ActorReference getActorId() {
        return actorId;
    }

    public void setActorId(ActorReference actorId) {
        this.actorId = actorId;
    }

    public MoviesEntity getMovieId() {
        return movieId;
    }

    public void setMovieId(MoviesEntity movieId) {
        this.movieId = movieId;
    }

    //    public void setId(RolesId id) {
//        this.id = id;
//    }


//
//    public ActorReference getActor() {
//        return actor;
//    }
//
//    public void setActor(ActorReference actor) {
//        this.actor = actor;
//    }


    public ActorReference getActor() {
        return actorId;
    }

    public void setActor(ActorReference actor) {
        this.actorId = actor;
    }


    public Integer getId(){
        return movieId.getMovieId();
    }



    @Override
    public String toString() {
        return "RolesEntity{" +
                "actorId=" + actorId +
                ", roleName='" + roleName + '\'' +
                ", movieId=" + movieId +
                '}';
    }
}
