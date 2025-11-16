package model;

import database.Koneksi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatatanDAO {
    private Connection conn;

    public CatatanDAO() {
        conn = Koneksi.getConnection();

        // Buat tabel otomatis
        try (Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS catatan (" +
                       "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                       "judul TEXT NOT NULL," +
                       "isi TEXT NOT NULL," +
                       "tanggal TEXT)");
        } catch (SQLException e) {
            System.out.println("Gagal membuat tabel: " + e.getMessage());
        }
    }

    public void tambahCatatan(Catatan c) {
        String sql = "INSERT INTO catatan (judul, isi, tanggal) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getJudul());
            ps.setString(2, c.getIsi());
            ps.setString(3, c.getTanggal());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menambah catatan: " + e.getMessage());
        }
    }

    public void ubahCatatan(Catatan c) {
        String sql = "UPDATE catatan SET judul=?, isi=?, tanggal=? WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getJudul());
            ps.setString(2, c.getIsi());
            ps.setString(3, c.getTanggal());
            ps.setInt(4, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal mengubah catatan: " + e.getMessage());
        }
    }

    public void hapusCatatan(int id) {
        String sql = "DELETE FROM catatan WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gagal menghapus catatan: " + e.getMessage());
        }
    }

    public List<Catatan> getAllCatatan() {
        List<Catatan> list = new ArrayList<>();
        String sql = "SELECT * FROM catatan ORDER BY id DESC";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Catatan c = new Catatan(
                    rs.getInt("id"),
                    rs.getString("judul"),
                    rs.getString("isi"),
                    rs.getString("tanggal")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }
        return list;
    }
}
