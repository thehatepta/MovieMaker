package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.MovieDaoInterface;
import com.eugen.moviemaker.entity.Movie;
import com.eugen.moviemaker.service.MovieServiceInterface;
import com.eugen.moviemaker.util.JsonJacksonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService implements MovieServiceInterface {

    private MovieDaoInterface movieDao;

    @Autowired
    public MovieService(MovieDaoInterface movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public String findAll() {
        List list = movieDao.findAll();
        String json = JsonJacksonConverter.convertToJson(list);
        return json;
    }

    @Override
    public String getThreeRandom() {
        List list = movieDao.getThreeRandom();
        String json = JsonJacksonConverter.convertToJson(list);
        return json;
    }

    @Override
    public String getAllGenres() {
        return null;
    }
}
