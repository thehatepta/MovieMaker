package com.eugen.moviemaker.service;

import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.GenreDaoInterface;
import com.eugen.moviemaker.service.GenreServiceInterface;
import com.eugen.moviemaker.util.JsonJacksonConverter;
import org.springframework.beans.factory.annotation.Autowired;

public class GenreService implements GenreServiceInterface {
    private GenreDaoInterface genreDao;

    @Autowired
    public GenreService(GenreDaoInterface genreDaoInterface) {
        this.genreDao = genreDaoInterface;
    }

    @Override
    public String getAllGenres() {
        return JsonJacksonConverter.convertToJson(genreDao.getAllGenres());
    }
}
