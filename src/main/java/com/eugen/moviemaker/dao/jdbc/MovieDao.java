package com.eugen.moviemaker.dao.jdbc;

import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.MovieDaoInterface;
import com.eugen.moviemaker.dao.jdbc.mapper.MovieRowMapper;
import com.eugen.moviemaker.entity.Movie;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class MovieDao implements MovieDaoInterface {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String FIND_ALL_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie;";
    private static final String FIND_RANDOM_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie LIMIT 5;";


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

}
