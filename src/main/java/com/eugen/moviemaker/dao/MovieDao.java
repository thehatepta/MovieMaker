package com.eugen.moviemaker.dao;


import com.eugen.moviemaker.entity.Movie;

import java.util.ArrayList;

public interface MovieDao<T> {

    ArrayList<T> findAll();
}
