package controller;

import model.*;
import java.util.List;

public class CatatanController {
    private CatatanDAO dao;

    public CatatanController() {
        dao = new CatatanDAO();
    }

    public void tambah(String judul, String isi, String tanggal) {
        Catatan c = new Catatan(judul, isi, tanggal);
        dao.tambahCatatan(c);
    }

    public void ubah(int id, String judul, String isi, String tanggal) {
        Catatan c = new Catatan(id, judul, isi, tanggal);
        dao.ubahCatatan(c);
    }

    public void hapus(int id) {
        dao.hapusCatatan(id);
    }

    public List<Catatan> tampilSemua() {
        return dao.getAllCatatan();
    }
}
