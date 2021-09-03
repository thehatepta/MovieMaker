package com.eugen.moviemaker.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnector {
    public String jdbcUrl;
    public String jdbcLogin;
    public String jdbcPassword;

    public JdbcConnector(Properties properties) throws JdbcDataException {
        jdbcUrl = properties.getProperty("DB_URL");
        jdbcLogin = properties.getProperty("DB_LOGIN");
        jdbcPassword = properties.getProperty("DB_PASSWORD");

        if (jdbcUrl == null) {
            throw new JdbcDataException("DB_URL is null");
        }
        if (jdbcLogin == null) {
            throw new JdbcDataException("DB_LOGIN is null");
        }
        if (jdbcPassword == null) {
            throw new JdbcDataException("DB_PASSWORD is null");
        }
    }

    public JdbcConnector()  {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, jdbcLogin, jdbcPassword);
    }

    public String getJdbcUrl() {
        return jdbcUrl;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcLogin() {
        return jdbcLogin;
    }

    public void setJdbcLogin(String jdbcLogin) {
        this.jdbcLogin = jdbcLogin;
    }

    public String getJdbcPassword() {
        return jdbcPassword;
    }

    public void setJdbcPassword(String jdbcPassword) {
        this.jdbcPassword = jdbcPassword;
    }
}
