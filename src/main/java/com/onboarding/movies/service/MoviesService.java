package com.onboarding.movies.service;

import com.onboarding.movies.dao.MoviesDAO;
import com.onboarding.movies.dao.RolesDao;
import com.onboarding.movies.mapper.ImageConversionHelper;
import com.onboarding.movies.mapper.ObjectUtilMapper;
import com.onboarding.movies.model.dto.GenresDTO;
import com.onboarding.movies.model.dto.MoviesCreationalDTO;
import com.onboarding.movies.model.dto.MoviesCreationalResponseDTO;
import com.onboarding.movies.model.entity.MoviesEntity;
import com.onboarding.movies.model.entity.RolesEntity;
import com.onboarding.movies.proxy.GenresClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MoviesService {

    @Autowired
    private GenresClient genresClient;
    @Autowired
    private MoviesDAO moviesDao;

    @Autowired
    private RolesDao rolesDao;

    @Autowired
    private ObjectUtilMapper mapper;


    public MoviesCreationalResponseDTO createMovies(MoviesCreationalDTO moviesCreationalDTO){

        ResponseEntity<GenresDTO> genresDTOResponseEntity = genresClient.getGenreById(moviesCreationalDTO.getGenreId());
        if(genresDTOResponseEntity.getBody() == null){
            throw new NoSuchElementException("There is no genre with id" + moviesCreationalDTO.getGenreId());
        }
        Integer genreId = genresDTOResponseEntity.getBody().getGenreId();
        String genreName = genresDTOResponseEntity.getBody().getGenreName();

        if(!moviesCreationalDTO.getMovieImage().equals("")) {
//            String[] imageData = ImageConversionHelper.splittedImageData(moviesCreationalDTO.getMovieImage());
//            String imageExtension = ImageConversionHelper.getImageExtension(imageData[0]);
//            UUID uuid = UUID.randomUUID();
//            String file = "src\\main\\resources\\static\\" + uuid + "." + imageExtension;
//
//            ImageConversionHelper.saveBase64Image(imageData[1], file);

            MoviesEntity moviesEntity = mapper.map(moviesCreationalDTO, MoviesEntity.class);
            List<RolesEntity> roles = mapper.mapList(moviesCreationalDTO.getRolesDTOList(), RolesEntity.class);



//            for (RolesEntity role: roles
//                 ) {
//                role.setMovieId(moviesEntity);
//            }
            System.out.println(moviesEntity);
            roles.forEach(System.out::println);
            //moviesEntity.setMovieImageName(uuid + "." + imageExtension);


            //MoviesEntity createdMovie = moviesDao.createMovie(moviesEntity);


        }
        return null;
    }
}
