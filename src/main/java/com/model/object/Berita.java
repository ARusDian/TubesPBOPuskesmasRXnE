package com.model.object;

import com.MVC.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Berita extends Model {
    private int id;
    private String judul;
    private String penulis;
    private String berita;

    public Berita(int id, String judul, String penulis, String berita) throws SQLException, ClassNotFoundException {
        super();
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.berita = berita;
    }

    public ArrayList<Berita> all() throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM berita");
        ArrayList<Berita> data = new ArrayList<>();
        while(result.next()){
            Berita temp = new Berita(
                    result.getInt("id"),
                    result.getString("judul"),
                    result.getString("penulis"),
                    result.getString("isi_berita")
            );
            data.add(temp);
        }
        return data;
    }

    public Berita find(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery(String.format("SELECT * FROM berita WHERE id = %d", id));
        return new Berita(
                result.getInt("id"),
                result.getString("judul"),
                result.getString("penulis"),
                result.getString("isi_berita")
        );

    }

    public void create(Berita data) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        int result = stmt.executeUpdate(
                String.format("INSERT INTO berita (judul, penulis, isi_berita, createdAt, updatedAt) " +
                                "VALUES ('%s', '%s', '%s', '%s', '%s')",
                        data.judul, data.penulis, data.berita, this.getTimeNow(), this.getTimeNow()));
    }

    public void update(Berita data, int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(
                String.format(
                        "UPDATE berita SET " +
                                "judul = '%s', " +
                                "penulis = '%s', " +
                                "isi_berita = '%s', " +
                                "updatedAt = '%s' " +
                                "WHERE id=%d",
                        data.judul, data.penulis, data.berita, this.getTimeNow() ,id
                )
        );
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        stmt.executeUpdate(String.format("DELETE FROM berita WHERE id = %d", id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getBerita() {
        return berita;
    }

    public void setBerita(String berita) {
        this.berita = berita;
    }
}
