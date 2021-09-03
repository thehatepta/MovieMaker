package com.eugen.moviemaker.dao.jdbc;

import com.eugen.moviemaker.dao.MovieDao;
import com.eugen.moviemaker.dao.jdbc.mapper.MovieRowMapper;
import com.eugen.moviemaker.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class MovieDaoImpl implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String FIND_ALL_MOVIES_QUERY = "SELECT id, name_russian, name_native,  year_of_release, description, rating, price, picture_path, votes FROM movie;";

    private JdbcConnector jdbcConnector;


    private MovieRowMapper movieRowMapper;

    public MovieDaoImpl(JdbcConnector jdbcConnector) {
        this.jdbcConnector = jdbcConnector;
    }

    @Override
    public ArrayList<Movie> findAll() {
        ArrayList<Movie> movies = new ArrayList<>();

        try (Connection connection = jdbcConnector.getConnection();
             PreparedStatement getAllStatement = connection.prepareStatement(FIND_ALL_MOVIES_QUERY)) {
            getAllStatement.execute();
            try (ResultSet resultSet = getAllStatement.getResultSet()) {
                while (resultSet.next()) {
                    Movie movie = getResultProducts(resultSet);
                    movies.add(movie);
                }
                return movies;
            }
        } catch (SQLException e) {
            throw new JdbcDataException("Unable to get products with id", e);
        }
    }

    private Movie getResultProducts(ResultSet resultSet) throws SQLException {

        return new Movie(resultSet.getInt("ID"),
                resultSet.getString("NAME_RUSSIAN"),
                resultSet.getString("NAME_NATIVE"),
                resultSet.getString("DESCRIPTION"),
                resultSet.getTimestamp("YEAR_OF_RELEASE"),
                resultSet.getInt("RATING"),
                resultSet.getDouble("PRICE"),
                resultSet.getString("PICTURE_PATH"),
                resultSet.getInt("VOTES"));
    }
}
