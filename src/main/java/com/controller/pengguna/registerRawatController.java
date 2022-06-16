package com.controller.pengguna;

import java.net.URL;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;


public class registerRawatController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/home.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRekamMedis(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/rekamMedis.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTolayarBerita(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/layarBerita.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchTolayarPengguna(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/dataPengguna.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtKeluhan;
    @FXML
    private DatePicker txtTanggal;
    @FXML
    private Label txtNamaIsi;
    @FXML
    private Label txtTanggalIsi;
    @FXML
    private Label txtKeluhanIsi;


    public void Isiform() {
        String Nama = txtNama.getText();
        String Keluhan = txtKeluhan.getText();
        LocalDate Tanggal = txtTanggal.getValue();


        txtNamaIsi.setText(Nama);
        txtTanggalIsi.setText(Tanggal.toString());
        txtKeluhanIsi.setText(Keluhan);

    }
    public void switchToFormRiwayat(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/formRiwayat.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
