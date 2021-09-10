package com.eugen.moviemaker.dao.jdbc.DaoInterfaces;

import java.util.List;

public interface GenreDaoInterface <T>{
    List<T> getAllGenres();
}
