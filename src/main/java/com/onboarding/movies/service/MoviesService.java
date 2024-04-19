package com.onboarding.movies.service;

import com.onboarding.movies.dao.MoviesDAO;
import com.onboarding.movies.dao.RolesDAO;
import com.onboarding.movies.exception.ResourceNotFoundException;
import com.onboarding.movies.mapper.FilePersistenceHelper;
import com.onboarding.movies.mapper.ImageConversionHelper;
import com.onboarding.movies.mapper.ObjectUtilMapper;
import com.onboarding.movies.model.dto.*;
import com.onboarding.movies.model.entity.MoviesEntity;
import com.onboarding.movies.model.entity.RolesEntity;
import com.onboarding.movies.proxy.ActorsClient;
import com.onboarding.movies.proxy.GenresClient;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MoviesService {

    @Autowired
    private GenresClient genresClient;
    @Autowired
    private ActorsClient actorsClient;
    @Autowired
    private MoviesDAO moviesDao;

    @Autowired
    private RolesDAO rolesDao;

    @Autowired
    private ObjectUtilMapper mapper;


    public MoviesCreationalResponseDTO createMovies(MoviesCreationalDTO moviesCreationalDTO) {

        ResponseEntity<GenresDTO> genresDTOResponseEntity = genresClient.getGenreById(moviesCreationalDTO.getGenreId());
        if (genresDTOResponseEntity.getBody() == null) {
            throw new NoSuchElementException("There is no genre with id" + moviesCreationalDTO.getGenreId());
        }
        String genreName = genresDTOResponseEntity.getBody().getGenreName();

        List<RolesResponseDTO> rolesResponseDTO = rolesResponseMapper(moviesCreationalDTO);

        if (!moviesCreationalDTO.getMovieImage().equals("")) {
            String[] imageData = ImageConversionHelper.splittedImageData(moviesCreationalDTO.getMovieImage());
            String imageExtension = ImageConversionHelper.getImageExtension(imageData[0]);
            UUID uuid = UUID.randomUUID();
            String file = "src\\main\\resources\\static\\" + uuid + "." + imageExtension;
            ImageConversionHelper.saveBase64Image(imageData[1], file);


            MoviesEntity moviesEntity = mapper.map(moviesCreationalDTO, MoviesEntity.class);
            moviesEntity.setMovieImageName(uuid + "." + imageExtension);
            MoviesEntity createdMovie = moviesDao.createMovie(moviesEntity);
            List<RolesEntity> rolesToCreate = mapper.mapList(moviesCreationalDTO.getRolesDTOList(), RolesEntity.class);
            List<RolesEntity> createdRoles = new ArrayList<>();
            rolesToCreate.forEach(r -> r.setMovieId(createdMovie.getMovieId()));
            rolesToCreate.forEach(r -> createdRoles.add(rolesDao.createRole(r)));

            MoviesCreationalResponseDTO movieResponseDTO = mapper.map(createdMovie, MoviesCreationalResponseDTO.class);
            movieResponseDTO.setMovieImageName(moviesCreationalDTO.getMovieImage());
            movieResponseDTO.setRoles(rolesResponseDTO);
            movieResponseDTO.setGenre(genreName);
            return movieResponseDTO;
        }
        return null;
    }


    public MoviesReadDTO getMoviebyId(Integer movieId) {
        MoviesEntity moviesEntity = moviesDao.getMoviesById(movieId);
        ResponseEntity<GenresDTO> genresDTOResponseEntity = genresClient.getGenreById(moviesEntity.getGenre());
        if (genresDTOResponseEntity.getBody() == null) {
            throw new NoSuchElementException("There is no genre with id" + moviesEntity.getGenre());
        }
        String genreName = genresDTOResponseEntity.getBody().getGenreName();

        List<RolesEntity> rolesEntities = rolesDao.getRolesByMovieId(movieId);
        List<RolesResponseDTO> rolesDTOS = new ArrayList<>();
        for (RolesEntity role : rolesEntities) {
            ResponseEntity<ActorsDTO> actorsDTOResponseEntity = actorsClient.getActorById(role.getActorId());
            RolesResponseDTO roleToReturn = mapper.map(actorsDTOResponseEntity.getBody(), RolesResponseDTO.class);
            roleToReturn.setRoleName(role.getRoleName());
            roleToReturn.setActorImage(actorsDTOResponseEntity.getBody().getActorsImage());
            rolesDTOS.add(roleToReturn);
        }
        MoviesReadDTO moviesReadDTO = mapper.map(moviesEntity, MoviesReadDTO.class);
        moviesReadDTO.setGenre(genreName);
        String file = "src\\main\\resources\\static\\" + moviesReadDTO.getMovieImage();
        String base64String = ImageConversionHelper.encodeImageToBase64(file);
        moviesReadDTO.setMovieImage(base64String);
        moviesReadDTO.setRoles(rolesDTOS);
        return moviesReadDTO;
    }

    public List<MoviesReadDTO> getMoviebyGenreId(Integer genreId) {
        List<MoviesEntity> moviesEntity = moviesDao.getMoviesByGenreId(genreId);
        List<MoviesReadDTO> moviesReadDTO = new ArrayList<>();

        for(MoviesEntity movieEntity: moviesEntity){

            MoviesReadDTO readMovie;
            readMovie = mapper.map(movieEntity, MoviesReadDTO.class);

            ResponseEntity<GenresDTO> genresDTOResponseEntity = genresClient.getGenreById(movieEntity.getGenre());
            if (genresDTOResponseEntity.getBody() == null) {
                throw new NoSuchElementException("There is no genre with id" + movieEntity.getGenre());
            }
            String genreName = genresDTOResponseEntity.getBody().getGenreName();
            List<RolesEntity> rolesEntities = rolesDao.getRolesByMovieId(movieEntity.getMovieId());
            List<RolesResponseDTO> rolesDTOS = new ArrayList<>();

            for (RolesEntity role : rolesEntities) {
                ResponseEntity<ActorsDTO> actorsDTOResponseEntity = actorsClient.getActorById(role.getActorId());
                RolesResponseDTO roleToReturn = mapper.map(actorsDTOResponseEntity.getBody(), RolesResponseDTO.class);
                roleToReturn.setRoleName(role.getRoleName());
                roleToReturn.setActorImage(actorsDTOResponseEntity.getBody().getActorsImage());
                rolesDTOS.add(roleToReturn);


            }
            readMovie.setGenre(genreName);
            String file = "src\\main\\resources\\static\\" + readMovie.getMovieImage();
            String base64String = ImageConversionHelper.encodeImageToBase64(file);
            readMovie.setMovieImage(base64String);
            readMovie.setRoles(rolesDTOS);
            moviesReadDTO.add(readMovie);
        }
        return moviesReadDTO;
    }


    public List<MoviesReadDTO> getAllMovies(){
        List<MoviesEntity> moviesEntity = moviesDao.getMovies();
        List<MoviesReadDTO> moviesReadDTO = new ArrayList<>();

        for(MoviesEntity movieEntity: moviesEntity){

            MoviesReadDTO readMovie;
            readMovie = mapper.map(movieEntity, MoviesReadDTO.class);

            ResponseEntity<GenresDTO> genresDTOResponseEntity = genresClient.getGenreById(movieEntity.getGenre());
            if (genresDTOResponseEntity.getBody() == null) {
                throw new NoSuchElementException("There is no genre with id" + movieEntity.getGenre());
            }
            String genreName = genresDTOResponseEntity.getBody().getGenreName();
            List<RolesEntity> rolesEntities = rolesDao.getRolesByMovieId(movieEntity.getMovieId());
            List<RolesResponseDTO> rolesDTOS = new ArrayList<>();

            for (RolesEntity role : rolesEntities) {
                ResponseEntity<ActorsDTO> actorsDTOResponseEntity = actorsClient.getActorById(role.getActorId());
                RolesResponseDTO roleToReturn = mapper.map(actorsDTOResponseEntity.getBody(), RolesResponseDTO.class);
                roleToReturn.setRoleName(role.getRoleName());
                roleToReturn.setActorImage(actorsDTOResponseEntity.getBody().getActorsImage());
                rolesDTOS.add(roleToReturn);


            }
            readMovie.setGenre(genreName);
            String file = "src\\main\\resources\\static\\" + readMovie.getMovieImage();
            String base64String = ImageConversionHelper.encodeImageToBase64(file);
            readMovie.setMovieImage(base64String);
            readMovie.setRoles(rolesDTOS);
            moviesReadDTO.add(readMovie);
        }
        return moviesReadDTO;
    }

    public void deleteMovie(Integer movieId) {
        MoviesEntity movieToDelete = moviesDao.getMoviesById(movieId);
        if (movieToDelete == null) {
            throw new ResourceNotFoundException("Movie with Id:" + movieId + " is not found.");
        }
        String file = "src\\main\\resources\\static\\" + movieToDelete.getMovieImageName();
        FilePersistenceHelper.removeFile(file);
        rolesDao.deleteRole(movieId);
        moviesDao.deleteMovie(movieId);
    }


    public MoviesCreationalResponseDTO updateMovie(MoviesCreationalDTO moviesCreationalDTO, Integer movieId) {
        MoviesEntity movieToUpdate = moviesDao.getMoviesById(movieId);
        if (movieToUpdate == null) {
            throw new ResourceNotFoundException("Movie with Id:" + movieId + " is not found.");
        }

        movieToUpdate.setMovieId(movieId);

        if (!Objects.equals(moviesCreationalDTO.getMovieImage(), "")) {
            if (movieToUpdate.getMovieImageName() == null) {

                String[] imageData = ImageConversionHelper.splittedImageData(moviesCreationalDTO.getMovieImage());
                String imageExtension = ImageConversionHelper.getImageExtension(imageData[0]);
                UUID uuid = UUID.randomUUID();
                String file = "src\\main\\resources\\static\\" + uuid + "." + imageExtension;
                ImageConversionHelper.saveBase64Image(imageData[1], file);
                moviesCreationalDTO.setMovieImageName(uuid + "." + imageExtension);
            } else {
                String[] imageData = ImageConversionHelper.splittedImageData(moviesCreationalDTO.getMovieImage());
                String imageExtension = ImageConversionHelper.getImageExtension(imageData[0]);

                String oldFile = movieToUpdate.getMovieImageName();
                String[] newFileSplit = oldFile.split("\\.");

                String newFile = "src\\main\\resources\\static\\" + newFileSplit[0] + "." + imageExtension;
                ImageConversionHelper.saveBase64Image(imageData[1], newFile);
                moviesCreationalDTO.setMovieImageName(newFileSplit[0] + "." + imageExtension);
            }
        }

        List<RolesResponseDTO> rolesResponseDTO = rolesResponseMapper(moviesCreationalDTO);

        rolesDao.deleteRole(movieId);

        List<RolesEntity> rolesToCreate = mapper.mapList(moviesCreationalDTO.getRolesDTOList(), RolesEntity.class);
        List<RolesEntity> createdRoles = new ArrayList<>();
        rolesToCreate.forEach(r -> r.setMovieId(movieId));
        rolesToCreate.forEach(r -> createdRoles.add(rolesDao.createRole(r)));

        MoviesEntity updatedMovie = moviesDao.updateMovie(movieId, mapper.map(moviesCreationalDTO, MoviesEntity.class));
        MoviesCreationalResponseDTO updatedActorDto = mapper.map(updatedMovie, MoviesCreationalResponseDTO.class);
        updatedActorDto.setMovieImage(moviesCreationalDTO.getMovieImage());
        updatedActorDto.setRoles(rolesResponseDTO);
        return updatedActorDto;


    }

    private List<RolesResponseDTO> rolesResponseMapper(MoviesCreationalDTO moviesCreationalDTO) {
        List<ResponseEntity<ActorsDTO>> actorsInMovie = new ArrayList<>();
        List<RolesResponseDTO> responseDTOList = new ArrayList<>();
        for (RolesDTO actor : moviesCreationalDTO.getRolesDTOList()) {
            try {
                ResponseEntity<ActorsDTO> returnedActor = actorsClient.getActorById(actor.getActorId());
                actorsInMovie.add(returnedActor);
                RolesResponseDTO roleResponseDTO = mapper.map(returnedActor.getBody(), RolesResponseDTO.class);
                roleResponseDTO.setRoleName(actor.getRoleName());
                roleResponseDTO.setActorImage(returnedActor.getBody().getActorsImage());
                responseDTOList.add(roleResponseDTO);
            } catch (FeignException ex) {
                throw new ResourceNotFoundException(ex.getMessage());
            }
        }
        return responseDTOList;
    }


}
