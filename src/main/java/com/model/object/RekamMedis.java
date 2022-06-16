package com.model.object;

import com.MVC.Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RekamMedis extends Model {
    private int id;
    private String diagnosaAwal;
    private String diagnosaAkhir;
    private String tindakan;
    private List<String> listObat;
    private int idDokter;
    private int idPasien;
    public RekamMedis(int id, String diagnosaAwal, String diagnosaAkhir, String tindakan, int idDokter, int idPasien, ArrayList<String> listObat)
            throws SQLException, ClassNotFoundException {
        this.id = id;
        this.diagnosaAwal = diagnosaAwal;
        this.diagnosaAkhir = diagnosaAkhir;
        this.tindakan = tindakan;
        this.idDokter = idDokter;
        this.idPasien = idPasien;
        this.listObat = listObat;
    }

    public void inputForm(RekamMedis data) throws SQLException, ClassNotFoundException{

    }

    public ArrayList<RekamMedis> all() throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM rekam_medis");
        ArrayList<String> dataObat = new ArrayList<>();
        ArrayList<RekamMedis> data = new ArrayList<>();
        while(result.next()){
            ArrayList<String> tempListObat = new ArrayList<>(Arrays.asList(result.getString("list_obat").split(",")));
            for(String d: tempListObat){
                Statement tempStmt = conn.createStatement();
                ResultSet tempResult = tempStmt.executeQuery("SELECT nama FROM obat WHERE id = " + Integer.parseInt(d));
                while(tempResult.next()){
                    dataObat.add(tempResult.getString("nama"));
                }

            }
            RekamMedis temp = new RekamMedis(
                    result.getInt("id"),
                    result.getString("diagnosa_awal"),
                    result.getString("diagnosa_akhir"),
                    result.getString("tindakan"),
                    result.getInt("id_dokter"),
                    result.getInt("id_pasien"),
                    dataObat
            );
            data.add(temp);
        }
        return data;
    }

    public RekamMedis find(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery(String.format("SELECT * FROM rekam_medis WHERE id = %d", id));
        ArrayList<String> dataObat = new ArrayList<>();
        if (result.next()) {
            ArrayList<String> tempListObat = new ArrayList<>(Arrays.asList(result.getString("list_obat").split(",")));
            for(String d: tempListObat){
                Statement tempStmt = this.conn.createStatement();
                ResultSet tempResult = tempStmt.executeQuery("SELECT nama FROM obat WHERE id = " + Integer.parseInt(d));
                while(tempResult.next()){
                    dataObat.add(tempResult.getString("nama"));
                }
            }
            return new RekamMedis(
                    result.getInt("id"),
                    result.getString("diagnosa_awal"),
                    result.getString("diagnosa_akhir"),
                    result.getString("tindakan"),
                    result.getInt("id_dokter"),
                    result.getInt("id_pasien"),
                    dataObat
            );
        }
        return null;
    }

    public ArrayList<RekamMedis> filter(String cari) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        ResultSet result = stmt.executeQuery(
                "SELECT * FROM rekam_medis JOIN users ON users.id = rekam_medis.id_pasien " +
                        "WHERE users.username LIKE '%" + cari + "%'"
        );
        ArrayList<String> dataObat = new ArrayList<>();
        ArrayList<RekamMedis> data = new ArrayList<>();
        while(result.next()){
            ArrayList<String> tempListObat = new ArrayList<>(Arrays.asList(result.getString("list_obat").split(",")));
            for(String d: tempListObat){
                Statement tempStmt = conn.createStatement();
                ResultSet tempResult = tempStmt.executeQuery("SELECT nama FROM obat WHERE id = " + Integer.parseInt(d));
                while(tempResult.next()){
                    dataObat.add(tempResult.getString("nama"));
                }
            }
            RekamMedis temp = new RekamMedis(
                    result.getInt("id"),
                    result.getString("diagnosa_awal"),
                    result.getString("diagnosa_akhir"),
                    result.getString("tindakan"),
                    result.getInt("id_dokter"),
                    result.getInt("id_pasien"),
                    dataObat
            );
            data.add(temp);
        }
        return data;
    }

    public void create(RekamMedis data) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        StringBuilder tempObat = new StringBuilder();
        for(String d: data.listObat){
            Statement tempStmt = this.conn.createStatement();
            ResultSet tempResult = tempStmt.executeQuery("SELECT id FROM obat WHERE nama = '" + d + "'");
            while(tempResult.next()){
                tempObat.append(tempResult.getInt("id")).append(",");
            }
        }
        stmt.executeUpdate(
                String.format(
                        "INSERT INTO rekam_medis (diagnosa_awal, diagnosa_akhir, tindakan, id_pasien, id_dokter, list_obat, createdAt, updatedAt) " +
                                "VALUES ('%s', '%s', '%s', '%d', '%d', '%s', '%s', '%s')",
                        data.diagnosaAwal, data.diagnosaAkhir, data.tindakan, data.idPasien, data.idDokter, tempObat.toString(),
                        this.getTimeNow(), this.getTimeNow()
                )
        );
    }

    public void update(RekamMedis data, int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        StringBuilder tempObat = new StringBuilder();
        for(String d: data.listObat){
            Statement tempStmt = conn.createStatement();
            ResultSet tempResult = tempStmt.executeQuery("SELECT id FROM obat WHERE nama = '" + d + "'");
            while(tempResult.next()){
                tempObat.append(tempResult.getInt("id")).append(",");
            }
        }
        stmt.executeUpdate(
                String.format(
                        "UPDATE `rekam_medis` SET " +
                                "diagnosa_awal = '%s', " +
                                "diagnosa_akhir = '%s', " +
                                "tindakan = '%s', " +
                                "id_dokter = '%d', " +
                                "id_pasien = '%d', " +
                                "list_obat = '%s', " +
                                "updatedAt = '%s' " +
                                "WHERE id=%d",
                        data.diagnosaAwal, data.diagnosaAkhir, data.tindakan, data.idDokter, data.idPasien, tempObat.toString(),
                        this.getTimeNow() ,id
                )
        );
    }

    public void delete(int id) throws SQLException, ClassNotFoundException {
        Statement stmt = this.conn.createStatement();
        int result = stmt.executeUpdate(String.format("DELETE FROM rekam_medis WHERE id = %d", id));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiagnosaAwal() {
        return diagnosaAwal;
    }

    public void setDiagnosaAwal(String diagnosaAwal) {
        this.diagnosaAwal = diagnosaAwal;
    }

    public String getDiagnosaAkhir() {
        return diagnosaAkhir;
    }

    public void setDiagnosaAkhir(String diagnosaAkhir) {
        this.diagnosaAkhir = diagnosaAkhir;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public List<String> getListObat() {
        return listObat;
    }

    public void setListObat(List<String> listObat) {
        this.listObat = listObat;
    }

    public int getIdDokter() {
        return idDokter;
    }

    public void setIdDokter(int idDokter) {
        this.idDokter = idDokter;
    }

    public int getIdPasien() {
        return idPasien;
    }

    public void setIdPasien(int idPasien) {
        this.idPasien = idPasien;
    }
}
























