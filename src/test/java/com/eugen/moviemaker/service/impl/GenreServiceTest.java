package com.eugen.moviemaker.service.impl;

import com.eugen.moviemaker.dao.jdbc.DaoInterfaces.GenreDaoInterface;
import com.eugen.moviemaker.dao.jdbc.GenreDao;
import com.eugen.moviemaker.dao.jdbc.mapper.GenreRowMapper;
import com.eugen.moviemaker.entity.Genre;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
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
    private static final String FIND_MOVIES_BY_GENRES_QUERY = "Select name_native from move as m inner join movie_genre as mg join genre as g where genere.id = 1";

    GenreService genreService;
    GenreDaoInterface genreDao;
    List postgresAllGenres = new ArrayList();

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
        Mockito.when(jdbcTemplate.query(eq(FIND_MOVIES_BY_GENRES_QUERY), any(GenreRowMapper.class)))
                .thenReturn(new ArrayList<>());
    }


    @Test
    public void testFindAllGenres() {
        String allGenresJson = genreService.getAllGenres();
        assertEquals(allGenresJson,"[{\"id\":1,\"name\":\"drama\"},{\"id\":2,\"name\":\"criminal\"},{\"id\":3,\"name\":\"fantasy\"}]");
    }
}
