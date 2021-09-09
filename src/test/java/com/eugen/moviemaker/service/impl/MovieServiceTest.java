package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.jdbc.MovieDao;
import com.eugen.moviemaker.dao.jdbc.mapper.MovieRowMapper;
import com.eugen.moviemaker.entity.Movie;
import com.eugen.moviemaker.util.JsonJacksonConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.eugen.moviemaker.dao.MovieDaoInterface;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class MovieServiceTest {

    private static final String FIND_ALL_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie;";
    private static final String FIND_RANDOM_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie LIMIT 5;";
    private static final String FIND_ALL_GENRES_QUERY = "SELECT distinct(name) FROM genre;";
    private static final String FIND_MOVIES_BY_GENRES_QUERY = "Select name_native from move as m inner join movie_genre as mg join genre as g where genere.id = ?";

    MovieDaoInterface movieDao;

    List postgresAllProducts = new ArrayList();
    List postgresAllGenres = new ArrayList();

    MovieRowMapper movieRowMapper = new MovieRowMapper();

    MovieService movieService;

    @Mock
    JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);

    @Mock
    private ResultSet resultSet;

    @BeforeEach
    public void mockData() {
        resultSet = mock(ResultSet.class);

        movieDao = new MovieDao();
        ReflectionTestUtils.setField(movieDao, "jdbcTemplate", jdbcTemplate);

        movieService = new MovieService(movieDao);

        try {
            when(resultSet.getLong("id")).thenReturn(1L);
            when(resultSet.getString("name_native")).thenReturn("Name");
            when(resultSet.getString("name_russian")).thenReturn("name_russian");
            when(resultSet.getString("description")).thenReturn("description");
            when(resultSet.getInt("year_of_release")).thenReturn(1994);
            when(resultSet.getInt("votes")).thenReturn(10);
            when(resultSet.getInt("rating")).thenReturn(100);
            when(resultSet.getDouble("price")).thenReturn(0.1D);
            when(resultSet.getString("picture_path")).thenReturn("picture.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        postgresAllProducts.add( new Movie(1, "name_russian", "name_native", "description", 1994, 100, 0.1D,"picture.com", 10));
        postgresAllProducts.add( new Movie(2, "name_russian", "name_native", "description", 1994, 100, 0.1D,"picture.com", 10));
        postgresAllProducts.add( new Movie(3, "name_russian", "name_native", "description", 1994, 100, 0.1D,"picture.com", 10));

        Mockito.when(jdbcTemplate.query(FIND_ALL_MOVIES_QUERY, movieRowMapper))
                .thenReturn(postgresAllProducts);
        Mockito.when(jdbcTemplate.query(FIND_RANDOM_MOVIES_QUERY, movieRowMapper))
                .thenReturn(postgresAllProducts);
        Mockito.when(jdbcTemplate.query(FIND_ALL_GENRES_QUERY, movieRowMapper))
                .thenReturn(postgresAllGenres);
        Mockito.when(jdbcTemplate.query(FIND_MOVIES_BY_GENRES_QUERY, movieRowMapper))
                .thenReturn(new ArrayList<>());


    }

    @Test
    public void testMapRow() {
        ArrayList allMovies = new ArrayList();
        try {
            Movie movie = movieRowMapper.mapRow(resultSet, 0);

            assertNotNull(movie);
            assertEquals("Name", movie.getName_native());
            assertEquals(1994, movie.getYear_of_release());
            assertEquals(100, movie.getRating());
            assertEquals(0.1D, movie.getPrice());
            assertEquals("picture.com", movie.getPicture_path());
            assertEquals("name_russian", movie.getName_russian());
            assertEquals("description", movie.getDescription());
            assertEquals(10, movie.getVotes());
            assertEquals(1L, movie.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindAll() {
        String allProductsJson = movieService.findAll();
        assertEquals(allProductsJson, JsonJacksonConverter.convertToJson(postgresAllProducts));
    }

    @Test
    public void testFindThreeRandom() {
        String listThreeJson = movieService.getThreeRandom();
        assertEquals(listThreeJson, JsonJacksonConverter.convertToJson(postgresAllProducts));
    }

    @Test
    public void testFindAllGenres() {
        String allGenresJson = movieService.getAllGenres();
        assertEquals(allGenresJson, JsonJacksonConverter.convertToJson(postgresAllGenres));
    }

}