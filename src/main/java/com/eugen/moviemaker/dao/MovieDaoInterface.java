package com.eugen.moviemaker.dao;


import com.eugen.moviemaker.entity.Movie;

import java.util.List;

public interface MovieDaoInterface<T> {

    List<T> findAll();
    List<T> getThreeRandom();
    List<T> getAllGenres();
}
