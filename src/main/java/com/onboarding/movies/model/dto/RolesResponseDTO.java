package com.onboarding.movies.model.dto;

public class RolesResponseDTO {
    Integer actorId;
    String actorImage;
    String imageName;
    String fullName;
    String roleName;

    public RolesResponseDTO() {
    }

    public RolesResponseDTO(Integer actorId, String actorImage, String imageName, String fullName, String roleName) {
        this.actorId = actorId;
        this.actorImage = actorImage;
        this.imageName = imageName;
        this.fullName = fullName;
        this.roleName = roleName;
    }


    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getActorImage() {
        return actorImage;
    }

    public void setActorImage(String actorImage) {
        this.actorImage = actorImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
