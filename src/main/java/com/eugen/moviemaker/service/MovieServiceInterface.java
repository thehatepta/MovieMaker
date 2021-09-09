package com.eugen.moviemaker.service;


import com.eugen.moviemaker.entity.Movie;

import java.util.List;

public interface MovieServiceInterface<T> {

    List<T> findAll();
}
