package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.MovieDaoInterface;
import com.eugen.moviemaker.service.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService implements MovieServiceInterface {

    private MovieDaoInterface movieDao;

    @Autowired
    public MovieService(MovieDaoInterface movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List findAll() {
        return movieDao.findAll();
    }
}
