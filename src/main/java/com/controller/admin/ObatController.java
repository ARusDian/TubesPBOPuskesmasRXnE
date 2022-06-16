package com.controller.admin;

import com.MVC.Controller;
import com.model.object.Obat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class ObatController extends Controller {
    private Obat obatData;
    private ObservableList<Obat> listObat;

    @FXML
    private TextField textfield_Aturan;

    @FXML
    private TextField textfield_Kandungan;

    @FXML
    private TextField textfield_efek;

    @FXML
    private TextField textfield_hargaobat;

    @FXML
    private TextField textfield_id;

    @FXML
    private TextField textfield_namaobat;

    @FXML
    private TextField textfield_stockobat;

    @FXML
    private TableView<Obat> tabelObat;

    @FXML
    private TableColumn<Obat, String> tabel_aturanpakai;


    @FXML
    private TableColumn<Obat, String> tabel_efeksamping;

    @FXML
    private TableColumn<Obat, Integer> tabel_hargaobat;

    @FXML
    private TableColumn<Obat, Integer> tabel_id;

    @FXML
    private TableColumn<Obat, String> tabel_kandungan;

    @FXML
    private TableColumn<Obat, String> tabel_namaobat;

    @FXML
    private TableColumn<Obat, Integer> tabel_stock;

    @FXML
    private TableColumn<Obat, String> tabel_tipe;

    @FXML
    private TextField textfield_tipeobat;

    public ObatController () throws SQLException, ClassNotFoundException {
        obatData = new Obat(
                0,
                "",
                "",
                "",
                "",
                "",
                0,
                0
        );
        this.listObat = FXCollections.observableList(obatData.all());

    }
    public void Show(){
        tabel_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabel_efeksamping.setCellValueFactory(new PropertyValueFactory<>("efekSamping"));
        tabel_aturanpakai.setCellValueFactory(new PropertyValueFactory<>("aturanKonsumsi"));
        tabel_hargaobat.setCellValueFactory(new PropertyValueFactory<>("harga"));
        tabel_kandungan.setCellValueFactory(new PropertyValueFactory<>("kandungan"));
        tabel_namaobat.setCellValueFactory(new PropertyValueFactory<>("name"));
        tabel_stock.setCellValueFactory(new PropertyValueFactory<>("stok"));
        tabel_tipe.setCellValueFactory(new PropertyValueFactory<>("tipe"));
        tabelObat.setItems(this.listObat);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.Show();
    }

    public void Logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDataPengguna(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/admin/user.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToDataObat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/admin/obat.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToDataRiwayat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/admin/rekamMedis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDataBerita(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/admin/berita.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void Insert(ActionEvent event) throws SQLException, ClassNotFoundException {
        int id = (!Objects.equals(textfield_id.getText(), "")) ? Integer.parseInt(textfield_id.getText()) : 0;
        Obat tempUser = new Obat(
                id,
                textfield_namaobat.getText(),
                textfield_Kandungan.getText(),
                textfield_Aturan.getText(),
                textfield_efek.getText(),
                textfield_tipeobat.getText(),
                Integer.parseInt(textfield_hargaobat.getText()),
                Integer.parseInt(textfield_stockobat.getText())
        );
        obatData.create(tempUser);
        this.Refresh(event);

    }

    @FXML
    protected void Delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        obatData.delete(Integer.parseInt(textfield_id.getText()));
        textfield_id.setText("");
        textfield_namaobat.setText("");
        textfield_Kandungan.setText("");
        textfield_Aturan.setText("");
        textfield_efek.setText("");
        textfield_tipeobat.setText("");
        textfield_hargaobat.setText("");
        textfield_stockobat.setText("");
        this.Refresh(event);

    }

    @FXML
    protected void Pilih(ActionEvent event) {
        Obat tempObat = tabelObat.getSelectionModel().getSelectedItem();
        if(tempObat != null){
            textfield_id.setText(String.valueOf(tempObat.getId()));
            textfield_namaobat.setText(String.valueOf(tempObat.getName()));
            textfield_Kandungan.setText(tempObat.getKandungan());
            textfield_Aturan.setText(tempObat.getAturanKonsumsi());
            textfield_efek.setText(tempObat.getEfekSamping());
            textfield_tipeobat.setText(tempObat.getTipe());
            textfield_hargaobat.setText(String.valueOf(tempObat.getHarga()));
            textfield_stockobat.setText(String.valueOf(tempObat.getStok()));
        }

    }

    @FXML
    protected void Refresh(ActionEvent event) throws SQLException, ClassNotFoundException {
        this.listObat = FXCollections.observableList(obatData.all());
        this.tabelObat.getItems().clear();
        this.Show();
    }

    @FXML
    protected void Edit(ActionEvent event) throws SQLException, ClassNotFoundException {
        Obat tempUser = new Obat(
                Integer.parseInt(textfield_id.getText()),
                textfield_namaobat.getText(),
                textfield_Kandungan.getText(),
                textfield_Aturan.getText(),
                textfield_efek.getText(),
                textfield_tipeobat.getText(),
                Integer.parseInt(textfield_hargaobat.getText()),
                Integer.parseInt(textfield_stockobat.getText())
        );
        obatData.update(tempUser,Integer.parseInt(textfield_id.getText()));
        this.Refresh(event);
    }
}

