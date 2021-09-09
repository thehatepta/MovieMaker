package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.jdbc.mappers.GenreRowMapper;
import com.eugen.moviemaker.dao.jdbc.mappers.MovieRowMapper;
import com.eugen.moviemaker.entity.Genre;
import com.eugen.moviemaker.entity.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MappersTests {

    MovieRowMapper movieRowMapper = new MovieRowMapper();
    GenreRowMapper genreRowMapper = new GenreRowMapper();


    @Mock
    private ResultSet resultSet;


    @Test
    public void testMovieMapRow() {
        ArrayList allMovies = new ArrayList();
        resultSet = mock(ResultSet.class);
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
    public void testGenreMapRow() {
        ArrayList allMovies = new ArrayList();
        resultSet = mock(ResultSet.class);
        try {
            when(resultSet.getLong("id")).thenReturn(1L);
            when(resultSet.getString("name")).thenReturn("drama");

            Genre genre = genreRowMapper.mapRow(resultSet, 0);

            assertNotNull(genre);

            assertEquals(1L, genre.getId());
            assertEquals("drama", genre.getName());


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
