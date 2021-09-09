package com.eugen.moviemaker.dao.jdbc.DaoInterfaces;


import com.eugen.moviemaker.entity.Movie;

import java.util.List;

public interface MovieDaoInterface<T> {

    List<T> findAll();
    List<T> getThreeRandom();
    List<T> getMoviesByGenre(int genre);
}
