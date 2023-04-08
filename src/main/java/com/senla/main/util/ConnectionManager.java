package com.senla.main.util;

import com.mysql.jdbc.Connection;

import java.sql.SQLException;
import java.util.Properties;

import static java.sql.DriverManager.*;

public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDriver();
    }

    public static Connection open() {
        try {
            return (Connection) getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(" ошибка загрузки драйвера ");
        }

    }


    private ConnectionManager() {

    }

}

