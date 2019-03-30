package com.suyang.mbg.utils;

import com.suyang.mbg.database.domain.DataSourceConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlUtils {

    private static final String driver = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection(DataSourceConfig config) throws ClassNotFoundException, SQLException {
        Connection conn = null;
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(getJdbcUrl(config), config.getUsername(), config.getPassword());
        } catch (ClassNotFoundException e) {
            throw e;
        } catch (SQLException e) {
            throw e;
        }
        return conn;
    }

    public static String getJdbcUrl(DataSourceConfig config) {
        return String.format("jdbc:mysql://%s:%s/%s", config.getHost(), config.getPort(), config.getDbName());
    }
}
