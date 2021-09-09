package com.eugen.moviemaker.dao.jdbc.mapper;

import com.eugen.moviemaker.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(resultSet.getLong("id"));
        movie.setName_russian(resultSet.getString("name_russian"));
        movie.setName_native(resultSet.getString("name_native"));
        movie.setDescription(resultSet.getString("description"));
        movie.setYear_of_release(resultSet.getInt("year_of_release"));
        movie.setPrice(resultSet.getDouble("price"));
        movie.setPicture_path(resultSet.getString("picture_path"));
        movie.setVotes(resultSet.getInt("votes"));
        movie.setRating(resultSet.getInt("rating"));
        return movie;
    }
}
