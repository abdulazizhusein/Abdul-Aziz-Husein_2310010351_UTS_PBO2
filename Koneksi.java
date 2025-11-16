package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static final String URL = "jdbc:sqlite:catatan.db"; // file lokal
    private static Connection conn;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(URL);
                System.out.println("Koneksi SQLite berhasil!");
            } catch (ClassNotFoundException e) {
                System.out.println("Driver SQLite tidak ditemukan: " + e.getMessage());
            } catch (SQLException e) {
                System.out.println("Koneksi SQLite gagal: " + e.getMessage());
            }
        }
        return conn;
    }
}
