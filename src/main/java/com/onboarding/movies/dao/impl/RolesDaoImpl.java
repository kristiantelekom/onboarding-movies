package com.onboarding.movies.dao.impl;

import com.onboarding.movies.dao.RolesDao;
import com.onboarding.movies.model.entity.RolesEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class RolesDaoImpl implements RolesDao {

    private EntityManager entityManager;

    @Override
    public RolesEntity createRole(RolesEntity rolesEntity) {
        entityManager.persist(rolesEntity);
        return entityManager.find(RolesEntity.class, rolesEntity.getId());
    }
}
