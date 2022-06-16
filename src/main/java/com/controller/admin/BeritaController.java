package com.controller.admin;

import com.MVC.Controller;
import com.model.object.Berita;
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

public class BeritaController extends Controller {
     private Berita beritaData;
    private ObservableList<Berita> listberita;

    @FXML
    private TextField textfield_id;

    @FXML
    private TextField textfield_isiberita;

    @FXML
    private TextField textfield_judul;

    @FXML
    private TextField textfield_penulis;

    @FXML
    private TableView<Berita> tabelBerita;

    @FXML
    private TableColumn<Berita, Integer> tabel_id;

    @FXML
    private TableColumn<Berita, String> tabel_isiBerita;

    @FXML
    private TableColumn<Berita, String> tabel_judul;

    @FXML
    private TableColumn<Berita, String> tabel_penulis;

    public BeritaController() throws SQLException, ClassNotFoundException {
        beritaData = new Berita(
                0,
                "",
                "",
                ""
        );
        this.listberita = FXCollections.observableList(beritaData.all());
    }

    @Override
    public void Show() {
        tabel_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabel_judul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        tabel_penulis.setCellValueFactory(new PropertyValueFactory<>("penulis"));
        tabel_isiBerita.setCellValueFactory(new PropertyValueFactory<>("berita"));
        tabelBerita.setItems(this.listberita);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        Berita tempBerita = new Berita(
                id,
                textfield_judul.getText(),
                textfield_penulis.getText(),
                textfield_isiberita.getText()
        );
        beritaData.create(tempBerita);
        this.Refresh(event);
    }

    @FXML
    public void Delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        beritaData.delete(Integer.parseInt(textfield_id.getText()));
        textfield_id.setText("");
        textfield_judul.setText("");
        textfield_penulis.setText("");
        textfield_isiberita.setText("");
        this.Refresh(event);
    }

    @FXML
    public void Pilih(ActionEvent event) {
        Berita berita = tabelBerita.getSelectionModel().getSelectedItem();
        if (berita != null) {
            textfield_id.setText(String.valueOf(berita.getId()));
            textfield_judul.setText(berita.getJudul());
            textfield_penulis.setText(berita.getPenulis());
            textfield_isiberita.setText(berita.getBerita());
        }
    }

    @FXML
    public void Refresh(ActionEvent event) throws SQLException, ClassNotFoundException {
        this.listberita = FXCollections.observableList(beritaData.all());
        this.tabelBerita.getItems().clear();
        this.Show();
    }

    @FXML
    public void Edit(ActionEvent event) throws SQLException, ClassNotFoundException {
        Berita tempBerita = new Berita(
                Integer.parseInt(textfield_id.getText()),
                textfield_judul.getText(),
                textfield_penulis.getText(),
                textfield_isiberita.getText()
        );
        beritaData.update(tempBerita, Integer.parseInt(textfield_id.getText()));
        this.Refresh(event);
    }
}


