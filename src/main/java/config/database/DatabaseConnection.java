package config.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection connection() {
        String url = "jdbc:sqlite:identifier.sqlite";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.out.println(String.format("Erro na conexão com o banco de dados: %s", e.getMessage()));
        }
        return conn;
    }
}
