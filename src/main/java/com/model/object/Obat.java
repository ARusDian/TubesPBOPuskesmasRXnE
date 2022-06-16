package com.model.object;

import com.MVC.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Obat extends Model {
    private int id;
    private String name;
    private String kandungan;
    private String aturanKonsumsi;
    private String efekSamping;
    private String tipe;
    private int harga;
    private int stok;

    public Obat(int id, String name, String kandungan, String aturanKonsumsi, String efekSamping, String tipe, int harga, int stok)
            throws SQLException, ClassNotFoundException {
        super();
        this.id = id;
        this.name = name;
        this.kandungan = kandungan;
        this.aturanKonsumsi = aturanKonsumsi;
        this.efekSamping = efekSamping;
        this.tipe = tipe;
        this.harga = harga;
        this.stok = stok;
    }

    public ArrayList<Obat> all() throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM obat");
        ArrayList<Obat> data = new ArrayList<>();
        while(result.next()){
            Obat temp = new Obat(
                    result.getInt("id"),
                    result.getString("nama"),
                    result.getString("kandungan"),
                    result.getString("aturan_konsumsi"),
                    result.getString("efek_samping"),
                    result.getString("tipe"),
                    result.getInt("harga"),
                    result.getInt("stok")
            );
            data.add(temp);
        }
        return data;
    }

    public Obat find(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery(String.format("SELECT * FROM obat WHERE id = %d", id));
        return new Obat(
                result.getInt("id"),
                result.getString("nama"),
                result.getString("kandungan"),
                result.getString("aturan_konsumsi"),
                result.getString("efek_samping"),
                result.getString("tipe"),
                result.getInt("harga"),
                result.getInt("stok")
        );
    }

    public ArrayList<Obat> filter(String cari) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM obat WHERE nama LIKE '%" + cari + "%'");
        ArrayList<Obat> data = new ArrayList<>();
        while(result.next()){
            Obat temp = new Obat(
                    result.getInt("id"),
                    result.getString("nama"),
                    result.getString("kandungan"),
                    result.getString("aturan_konsumsi"),
                    result.getString("efek_samping"),
                    result.getString("tipe"),
                    result.getInt("harga"),
                    result.getInt("stok")
            );
            data.add(temp);
        }
        return data;
    }

    public void create(Obat data) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        int result = stmt.executeUpdate(
                String.format(
                        "INSERT INTO obat (nama, kandungan, aturan_konsumsi, efek_samping, tipe, harga, stok, createdAt, updatedAt) " +
                                "VALUES ('%s', '%s', '%s', '%s', '%s', '%d', '%d', '%s', '%s')",
                        data.name, data.kandungan, data.aturanKonsumsi, data.efekSamping, data.tipe, data.harga, data.stok,
                        this.getTimeNow(), this.getTimeNow()
                )
        );
    }

    public void update(Obat data, int id) throws SQLException, ClassNotFoundException {
//        Connection conn = Database.getConnection();
        Statement stmt = this.conn.createStatement();
        int result = stmt.executeUpdate(
                String.format(
                        "UPDATE `obat` SET " +
                                "nama = '%s', " +
                                "kandungan = '%s', " +
                                "aturan_konsumsi = '%s', " +
                                "efek_samping = '%s', " +
                                "tipe = '%s', " +
                                "harga = '%d', " +
                                "stok = '%d', " +
                                "updatedAt = '%s' " +
                                "WHERE id=%d",
                        data.name, data.kandungan, data.aturanKonsumsi, data.efekSamping, data.tipe, data.harga, data.stok,
                        this.getTimeNow() ,id
                )
        );
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        int result = stmt.executeUpdate(String.format("DELETE FROM obat WHERE id = %d", id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKandungan() {
        return kandungan;
    }

    public void setKandungan(String kandungan) {
        this.kandungan = kandungan;
    }

    public String getAturanKonsumsi() {
        return aturanKonsumsi;
    }

    public void setAturanKonsumsi(String aturanKonsumsi) {
        this.aturanKonsumsi = aturanKonsumsi;
    }

    public String getEfekSamping() {
        return efekSamping;
    }

    public void setEfekSamping(String efekSamping) {
        this.efekSamping = efekSamping;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
