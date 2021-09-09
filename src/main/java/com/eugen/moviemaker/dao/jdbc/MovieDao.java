package com.eugen.moviemaker.dao.jdbc;

import com.eugen.moviemaker.dao.MovieDaoInterface;
import com.eugen.moviemaker.dao.jdbc.mapper.MovieRowMapper;
import com.eugen.moviemaker.entity.Movie;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@AllArgsConstructor
public class MovieDao implements MovieDaoInterface {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String FIND_ALL_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie;";
    private static final String FIND_RANDOM_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie LIMIT 5;";
    private static final String FIND_ALL_GENRES_QUERY = "SELECT distinct(name) FROM genre;";
    private static final String FIND_MOVIES_BY_GENRES_QUERY = "Select name_native from move as m inner join movie_genre as mg join genre as g where genere.id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final MovieRowMapper MOVIE_ROW_MAPPER = new MovieRowMapper();

    public MovieDao() {
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query(FIND_ALL_MOVIES_QUERY, MOVIE_ROW_MAPPER);
    }

    @Override
    public List<Movie> getThreeRandom() {
        return jdbcTemplate.query(FIND_RANDOM_MOVIES_QUERY, MOVIE_ROW_MAPPER);
    }

    @Override
    public List getAllGenres() {
        return null;
    }

}
