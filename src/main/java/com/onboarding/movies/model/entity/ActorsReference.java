package com.onboarding.movies.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ActorsReference {

    @Id
    @Column(name = "actor_id")
    private Integer actorId;
    private String roleName;

    public ActorsReference() {
    }

    public ActorsReference(Integer actorId, String roleName) {
        this.actorId = actorId;
        this.roleName = roleName;
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
