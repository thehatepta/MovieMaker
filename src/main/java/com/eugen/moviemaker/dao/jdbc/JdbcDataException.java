package com.eugen.moviemaker.dao.jdbc;

public class JdbcDataException extends RuntimeException {
    public JdbcDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcDataException(String message) {
        super(message);
    }
}
