package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.GenreDaoInterface;
import com.eugen.moviemaker.dao.jdbc.GenreDao;
import com.eugen.moviemaker.dao.jdbc.mappers.GenreRowMapper;
import com.eugen.moviemaker.entity.Genre;
import com.eugen.moviemaker.service.GenreService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GenreServiceTest {
    private static final String FIND_ALL_GENRES_QUERY = "SELECT distinct(name) FROM genre;";


    GenreService genreService;
    GenreDaoInterface genreDao;
    List<Genre> postgresAllGenres = new ArrayList();


    @Mock
    JdbcTemplate jdbcTemplate = Mockito.mock(JdbcTemplate.class);

    @BeforeAll
    public void prepare(){

        genreDao = new GenreDao();
        ReflectionTestUtils.setField(genreDao, "jdbcTemplate", jdbcTemplate);
        genreService = new GenreService(genreDao);

        postgresAllGenres.add(new Genre(1, "drama"));
        postgresAllGenres.add(new Genre(2, "criminal"));
        postgresAllGenres.add(new Genre(3, "fantasy"));

        Mockito.when(jdbcTemplate.query(eq(FIND_ALL_GENRES_QUERY), any(GenreRowMapper.class)))
                .thenReturn(postgresAllGenres);
    }


    @Test
    public void testFindAllGenres() {
        String allGenresJson = genreService.getAllGenres();
        assertEquals(allGenresJson,"[{\"id\":1,\"name\":\"drama\"},{\"id\":2,\"name\":\"criminal\"},{\"id\":3,\"name\":\"fantasy\"}]");
    }


}
