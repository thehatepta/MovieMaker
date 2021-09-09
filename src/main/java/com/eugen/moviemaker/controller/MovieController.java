package com.eugen.moviemaker.controller;


import com.eugen.moviemaker.entity.Movie;
import com.eugen.moviemaker.service.GenreServiceInterface;
import com.eugen.moviemaker.service.MovieServiceInterface;

import com.eugen.moviemaker.util.JsonJacksonConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(path = "/api/v1")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieServiceInterface movieService;

    @Autowired
    private GenreServiceInterface genreService;

    @GetMapping(path = "/movie")
    public String getAllMovies(Model model) {
        String json = movieService.findAll();
        return json;
    }

    @GetMapping(path = "/movie/random")
    public String getRandom(Model model) {
        String json = movieService.getThreeRandom();
        return json;
    }

    @GetMapping(path = "/movie/genre")
    public String findAllGenres(Model model) {
        String json = genreService.getAllGenres();
        return json;
    }

}
