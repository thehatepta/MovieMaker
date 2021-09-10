package com.eugen.moviemaker.dao.jdbc.DaoInterfaces;


import com.eugen.moviemaker.entity.Movie;

import java.util.List;

public interface MovieDaoInterface<T> {

    List<T> findAll();
    List<T> getThreeRandom();
    List<T> getMoviesByGenre(int genre);
    List<T> sortMoviesByRating(String ratingOrder);
    List<T> sortMoviesByPrice(String priceOrder);
    List<T> sortMoviesByGenreAndPrice(int genre, String priceOrder);
    List<T> sortMoviesByGenreAndRating(int genre, String ratingOrder);
}
