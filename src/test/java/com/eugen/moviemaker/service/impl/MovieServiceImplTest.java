package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.jdbc.JdbcConnector;
import com.eugen.moviemaker.dao.jdbc.MovieDao;
import com.eugen.moviemaker.dao.jdbc.mapper.MovieRowMapper;
import com.eugen.moviemaker.entity.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.eugen.moviemaker.dao.MovieDaoInterface;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MovieServiceImplTest {

    List postgresAllProducts = new ArrayList();
    List postgresThreeMovies = new ArrayList();
    List postgresAllGenres = new ArrayList();

    @Mock
    private ResultSet resultSet;

    MovieDaoInterface moviedao = new MovieDao();

    @BeforeAll
    public void mockData() {
        resultSet = mock(ResultSet.class);
        try {
            when(resultSet.getLong("id")).thenReturn(1L);
            when(resultSet.getString("name_native")).thenReturn("Name");
            when(resultSet.getString("name_russian")).thenReturn("name_russian");
            when(resultSet.getString("description")).thenReturn("description");
            when(resultSet.getInt("year_of_release")).thenReturn(1997);
            when(resultSet.getInt("votes")).thenReturn(10);
            when(resultSet.getInt("rating")).thenReturn(999);
            when(resultSet.getDouble("price")).thenReturn(0.1D);
            when(resultSet.getString("picture_path")).thenReturn("picture.com");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testMapRow() {
        ArrayList allMovies = new ArrayList();
        try {
            MovieRowMapper movieRowMapper = new MovieRowMapper();
            Movie movie = movieRowMapper.mapRow(resultSet, 0);

            assertNotNull(movie);
            assertEquals("Name", movie.getName_native());
            assertEquals(1997, movie.getYear_of_release());
            assertEquals(999, movie.getRating());
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
        List allProducts = moviedao.findAll();
        assertEquals(allProducts, postgresAllProducts);
    }

    @Test
    public void testFindThreeRandom() {
        List listThree = moviedao.getThreeRandom();
        assertEquals(listThree, postgresThreeMovies);
    }

    @Test
    public void testFindAllGenrs() {
        List allGenres = moviedao.getAllGenres();
        assertEquals(allGenres, postgresAllGenres);
    }

}