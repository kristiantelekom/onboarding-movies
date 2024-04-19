package com.onboarding.movies.dao.impl;

import com.onboarding.movies.dao.RolesDAO;
import com.onboarding.movies.exception.InternalServerErrorException;
import com.onboarding.movies.exception.ResourceNotFoundException;
import com.onboarding.movies.helper.Helper;
import com.onboarding.movies.helper.UtilQueryBuilder;
import com.onboarding.movies.model.entity.RolesEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@Transactional
public class RolesDAOImpl implements RolesDAO {

    @Autowired
    NamedParameterJdbcTemplate namedJdbcTemplate;

    @Autowired
    Helper helper;

    @Autowired
    UtilQueryBuilder utilQueryBuilder;

    @Override
    public RolesEntity createRole(RolesEntity rolesEntity) {

        String sql = utilQueryBuilder.insertIntoRoles() + " VALUES(:movie_id, :actor_id, :role_name)";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("movie_id", rolesEntity.getMovieId());
        parameters.put("actor_id", rolesEntity.getActorId());
        parameters.put("role_name", rolesEntity.getRoleName());
        try {
            SqlParameterSource namedParameters = new MapSqlParameterSource().addValues(parameters);
            namedJdbcTemplate.update(sql, namedParameters);
            return rolesEntity;
        } catch (DataAccessException e) {
            //logger.error("DataAccessException exception occurred -> " + helper.convertObjectToString(e));
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public List<RolesEntity> getRolesByMovieId(Integer movieId) {
        String sql = utilQueryBuilder.getSelectInitialQuery() + utilQueryBuilder.getRolesTableName() + " WHERE movie_id = :movieId";
        ;
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("movieId", movieId);
        try {
            return namedJdbcTemplate.query(sql, namedParameters, BeanPropertyRowMapper.newInstance(RolesEntity.class));
        } catch (EmptyResultDataAccessException ex) {

            throw new ResourceNotFoundException("Role with movie ID " + movieId + " does not exist");
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public void deleteRole(Integer movieId) {
        String sql = utilQueryBuilder.deleteFrom() + utilQueryBuilder.getRolesTableName() + " WHERE movie_id = :movieId";
        SqlParameterSource namedParameers = new MapSqlParameterSource().addValue("movieId", movieId);
        try {
            namedJdbcTemplate.update(sql, namedParameers);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException("Role with movie ID " + movieId + " does not exist");
        } catch (DataAccessException e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
