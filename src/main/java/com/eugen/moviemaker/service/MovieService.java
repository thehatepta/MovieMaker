package com.eugen.moviemaker.service;


import com.eugen.moviemaker.entity.Movie;

import java.util.ArrayList;

public interface MovieService<T> {

    ArrayList<T> findAll();
}
