package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.entity.Movie;
import com.eugen.moviemaker.dao.MovieDao;
import com.eugen.moviemaker.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public ArrayList findAll() {
        return movieDao.findAll();
    }
}
