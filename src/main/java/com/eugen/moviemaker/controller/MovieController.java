package com.eugen.moviemaker.controller;


import com.eugen.moviemaker.entity.Movie;
import com.eugen.moviemaker.service.MovieService;

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
@RequestMapping(path = "/movies")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieService movieService;

    @GetMapping
    public String getAllMovies(Model model) {
        ArrayList<Movie> allMovies = movieService.findAll();
        String json =JsonJacksonConverter.convertToJson(allMovies);
        return json;
    }

}
