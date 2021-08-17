package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static Connection connection;
    private static final String url = "jdbc:mysql://localhost:3306/base";
    private static final String userName = "root";
    private static final String password = "1234";

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(url, userName, password);
            System.out.println("Соединение с базой данных установлено!");
        }catch (SQLException e) {
            System.out.println("Соединение с базой данных не установлено!");
        }
        return connection;
    }
}