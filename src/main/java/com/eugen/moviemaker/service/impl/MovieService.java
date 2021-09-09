package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.MovieDaoInterface;
import com.eugen.moviemaker.service.MovieServiceInterface;
import com.eugen.moviemaker.util.JsonJacksonConverter;
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
    public String findAll() {
        return JsonJacksonConverter.convertToJson(movieDao.findAll());
    }

    @Override
    public String getThreeRandom() {
        return JsonJacksonConverter.convertToJson(movieDao.getThreeRandom());
    }
}
