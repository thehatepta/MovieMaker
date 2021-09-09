package com.eugen.moviemaker.controller;

import com.eugen.moviemaker.service.GenreServiceInterface;
import com.eugen.moviemaker.service.MovieServiceInterface;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/api/v1/movie")
public class MovieController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MovieServiceInterface movieService;

    @Autowired
    private GenreServiceInterface genreService;

    @GetMapping()
    public String getAllMovies() {
        String json = movieService.findAll();
        return json;
    }

    @GetMapping(params = {"rating"})
    public String sortMoviesByRating(@RequestParam(name = "rating") String ratingSortingOrder){
        String json = movieService.sortMoviesByRating(ratingSortingOrder);
        return json;
    }

    @GetMapping(params = {"price"})
    public String sortMoviesByPrice(@RequestParam(name = "price") String priceSortingOrder){
        String json = movieService.sortMoviesByPrice(priceSortingOrder);
        return json;
    }

    @GetMapping(path = "/random")
    public String getRandom() {
        String json = movieService.getThreeRandom();
        return json;
    }


    @GetMapping(path = "/genre")
    public String findAllGenres() {
        String json = genreService.getAllGenres();
        return json;
    }

    @GetMapping(path = "/genre/{genreId}")
    public String findMoviesBYGenres(@PathVariable("genreId") int genreId) {
        String json = movieService.getMoviesByGenre(genreId);
        return json;
    }

    @GetMapping(path = "/genre/{genreId}", params = {"price"})
    public String findMoviesBYGenresSortByPrice(@PathVariable("genreId") int genreId, @RequestParam(name = "price") String priceSortingOrder) {
        String json = movieService.getMoviesByGenre(genreId);
        return json;
    }

    @GetMapping(path = "/genre/{genreId}", params = {"price"})
    public String findMoviesBYGenresSortByRating(@PathVariable("genreId") int genreId, @RequestParam(name = "rating") String ratingSortingOrder) {
        String json = movieService.getMoviesByGenre(genreId);
        return json;
    }

}
