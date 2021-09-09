package com.eugen.moviemaker.service;

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

    @Override
    public String getMoviesByGenre(int genre) {
        return JsonJacksonConverter.convertToJson(movieDao.getMoviesByGenre(genre));
    }

    @Override
    public String sortMoviesByRating(String ratingOrder) {
        return JsonJacksonConverter.convertToJson(movieDao.sortMoviesByRating(ratingOrder));
    }

    @Override
    public String sortMoviesByPrice(String priceOrder) {
        return JsonJacksonConverter.convertToJson(movieDao.sortMoviesByPrice(priceOrder));
    }
}
