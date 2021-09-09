package com.eugen.moviemaker.dao.jdbc;

import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.GenreDaoInterface;
import com.eugen.moviemaker.dao.jdbc.mapper.GenreRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class GenreDao implements GenreDaoInterface {
    private static final String FIND_ALL_GENRES_QUERY = "SELECT distinct(name) FROM genre;";
    private static final String FIND_MOVIES_BY_GENRES_QUERY = "Select name_native from move as m inner join movie_genre as mg join genre as g where genere.id = ?";

    private JdbcTemplate jdbcTemplate;

    private static final GenreRowMapper GENRE_ROW_MAPPER = new GenreRowMapper();

    public GenreDao() {
    }

    @Autowired
    public GenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List getAllGenres() {
        return jdbcTemplate.query(FIND_ALL_GENRES_QUERY, GENRE_ROW_MAPPER);
    }
}