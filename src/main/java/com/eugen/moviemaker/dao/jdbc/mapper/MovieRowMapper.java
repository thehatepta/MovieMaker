package com.eugen.moviemaker.dao.jdbc.mapper;

import com.eugen.moviemaker.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getLong("id"));
        movie.setName_russian(rs.getString("name_russian"));
        movie.setName_native(rs.getString("name_native"));
        movie.setDescription(rs.getString("description"));
        movie.setYear_of_release(rs.getInt("year_of_release"));
        movie.setPrice(rs.getDouble("price"));
        movie.setPicture_path(rs.getString("picture_path"));
        movie.setVotes(rs.getInt("votes"));
        movie.setRating(rs.getInt("rating"));
        return movie;
    }
}
