package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.GenreDaoInterface;
import com.eugen.moviemaker.dao.jdbc.GenreDao;
import com.eugen.moviemaker.dao.jdbc.MovieDao;
import com.eugen.moviemaker.dao.jdbc.mapper.GenreRowMapper;
import com.eugen.moviemaker.dao.jdbc.mapper.MovieRowMapper;
import com.eugen.moviemaker.entity.Genre;
import com.eugen.moviemaker.entity.Movie;
import com.eugen.moviemaker.util.JsonJacksonConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.MovieDaoInterface;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MovieServiceTest {

    private static final String FIND_ALL_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie;";
    private static final String FIND_RANDOM_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie LIMIT 5;";


    MovieDaoInterface movieDao;

    List postgresAllProducts = new ArrayList();


    MovieService movieService;

    @Mock
    JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);


    @BeforeAll
    public void mockData() {


        movieDao = new MovieDao();
        ReflectionTestUtils.setField(movieDao, "jdbcTemplate", jdbcTemplate);

        movieService = new MovieService(movieDao);



        postgresAllProducts.add(new Movie(1, "name_russian", "name_native", "description", 1994, 100, 0.1D, "picture.com", 10));
        postgresAllProducts.add(new Movie(2, "name_russian", "name_native", "description", 1994, 100, 0.1D, "picture.com", 10));
        postgresAllProducts.add(new Movie(3, "name_russian", "name_native", "description", 1994, 100, 0.1D, "picture.com", 10));



        Mockito.when(jdbcTemplate.query(eq(FIND_ALL_MOVIES_QUERY), any(MovieRowMapper.class)))
                .thenReturn(postgresAllProducts);
        Mockito.when(jdbcTemplate.query(eq(FIND_RANDOM_MOVIES_QUERY), any(MovieRowMapper.class)))
                .thenReturn(postgresAllProducts);
    }



    @Test
    public void testFindAll() {
        String allProductsJson = movieService.findAll();
        assertEquals(allProductsJson, "[{\"id\":1,\"name_russian\":\"name_russian\",\"name_native\":\"name_native\",\"description\":\"description\",\"year_of_release\":1994,\"rating\":100,\"price\":0.1,\"picture_path\":\"picture.com\",\"votes\":10},{\"id\":2,\"name_russian\":\"name_russian\",\"name_native\":\"name_native\",\"description\":\"description\",\"year_of_release\":1994,\"rating\":100,\"price\":0.1,\"picture_path\":\"picture.com\",\"votes\":10},{\"id\":3,\"name_russian\":\"name_russian\",\"name_native\":\"name_native\",\"description\":\"description\",\"year_of_release\":1994,\"rating\":100,\"price\":0.1,\"picture_path\":\"picture.com\",\"votes\":10}]");
    }

    @Test
    public void testFindThreeRandom() {
        String listThreeJson = movieService.getThreeRandom();
        assertEquals(listThreeJson, "[{\"id\":1,\"name_russian\":\"name_russian\",\"name_native\":\"name_native\",\"description\":\"description\",\"year_of_release\":1994,\"rating\":100,\"price\":0.1,\"picture_path\":\"picture.com\",\"votes\":10},{\"id\":2,\"name_russian\":\"name_russian\",\"name_native\":\"name_native\",\"description\":\"description\",\"year_of_release\":1994,\"rating\":100,\"price\":0.1,\"picture_path\":\"picture.com\",\"votes\":10},{\"id\":3,\"name_russian\":\"name_russian\",\"name_native\":\"name_native\",\"description\":\"description\",\"year_of_release\":1994,\"rating\":100,\"price\":0.1,\"picture_path\":\"picture.com\",\"votes\":10}]");
    }



}