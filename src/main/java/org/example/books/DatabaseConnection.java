package org.example.books;
import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/library_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Ser2005";

    private Connection connection;

    public DatabaseConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Подключение к базе данных успешно!");
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}