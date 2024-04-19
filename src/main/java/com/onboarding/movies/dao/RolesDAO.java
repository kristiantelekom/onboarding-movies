package com.onboarding.movies.dao;

import com.onboarding.movies.model.entity.RolesEntity;

import java.util.List;

public interface RolesDAO {

    RolesEntity createRole(RolesEntity rolesEntity);

    List<RolesEntity> getRolesByMovieId(Integer movieId);

    void deleteRole(Integer movieId);
}
