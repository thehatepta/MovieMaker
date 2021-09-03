package com.eugen.moviemaker.util;

import com.eugen.moviemaker.entity.Movie;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonJacksonConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    // thread-safe
    private ObjectMapper objectMapper = new ObjectMapper();

    public Movie parseCity(String json) {
        log.info("Start parsing city from json {}", json);
        long startTime = System.currentTimeMillis();
        Movie movie = parseValue(json, Movie.class);
        long time = System.currentTimeMillis() - startTime;
        log.info("City {} is received. It took {} ms", movie, time);
        return movie;
    }

    public static String convertToJson(ArrayList<Movie> movie) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(movie);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private <T> T parseValue(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
