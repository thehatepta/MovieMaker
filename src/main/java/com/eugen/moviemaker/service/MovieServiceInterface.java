package com.eugen.moviemaker.service;


import com.eugen.moviemaker.entity.Movie;

import java.util.List;

public interface MovieServiceInterface<T> {

    String findAll();
    String getThreeRandom();
    String getMoviesByGenre(int genre);
    String sortMoviesByRating(String ratingOrder);
    String sortMoviesByPrice(String priceOrder);
    String sortMoviesByGenreAndRating(int genre, String ratingOrder);
    String sortMoviesByGenreAndPrice(int genre, String priceOrder);

}
