package com.eugen.moviemaker.util;

import com.eugen.moviemaker.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class JsonManualConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private static final String COMMA_SEPARATOR = ",";
    private static final String COLON_SEPARATOR = ":";

    public String toJson(Movie movie) {
        log.info("Start converting city {} to json", movie);
        StringBuilder json = new StringBuilder("{");
        String[] cityFieldNames = {"id", "name", "district", "population"};
        Object[] cityFields = {movie.getId(), movie.getName_russian(), movie.getName_native(), movie.getRating()};
        for (int i = 0; i < cityFieldNames.length; i++) {
            json.append(surroundByQuotes(cityFieldNames[i]));
            json.append(COLON_SEPARATOR);
            json.append(surroundByQuotes(cityFields[i]));
            if (i != cityFieldNames.length - 1) {
                json.append(COMMA_SEPARATOR);
            }
        }
        json.append("}");
        log.info("Receiving city as json {}", json);
        return json.toString();
    }

    private String surroundByQuotes(Object value) {
        return "\"" + value + "\"";
    }
}
