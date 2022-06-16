package com.controller.pengguna;

import com.MVC.Controller;
import com.model.object.Obat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.StandardSocketOptions;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BeliObatController extends Controller {
    private Obat obatData;
    @FXML
    private GridPane gridBerita;

    @FXML
    private ScrollPane scrollBerita;

    private Stage stage;
    private Scene scene;
    private List<Obat> listObat;

    @FXML
    private Label textfield_Aturan;

    @FXML
    private Label textfield_Kandungan;

    @FXML
    private Label textfield_efek;

    @FXML
    private Label textfield_hargaobat;

    @FXML
    private Label textfield_id;

    @FXML
    private Label textfield_namaobat;

    @FXML
    private TextField textfield_stockobat;

    @FXML
    private Label textfield_tipeobat;

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
    private TextField textfield_total;

    public BeliObatController() throws SQLException, ClassNotFoundException {
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.Show();
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
        tabelObat.setItems((ObservableList<Obat>) this.listObat);
    }

    @FXML
    public void Kalkulasi(){
        Obat tempObat = tabelObat.getSelectionModel().getSelectedItem();
        if(tempObat != null){
            textfield_id.setText(String.valueOf(tempObat.getId()));
            textfield_namaobat.setText(String.valueOf(tempObat.getName()));
            textfield_Kandungan.setText(tempObat.getKandungan());
            textfield_Aturan.setText(tempObat.getAturanKonsumsi());
            textfield_efek.setText(tempObat.getEfekSamping());
            textfield_tipeobat.setText(tempObat.getTipe());
            textfield_hargaobat.setText(String.valueOf(tempObat.getHarga()));
        }
        int jumlah = Integer.parseInt(textfield_stockobat.getText());
        int harga = Integer.parseInt(textfield_hargaobat.getText());
        textfield_total.setText(String.valueOf(jumlah*harga));
    }

    @FXML
    public void Beli() throws FileNotFoundException {
        PrintWriter out = new PrintWriter("Struk.txt"); // Step 2

        out.println("Struk Obat");
        out.println("Nama Obat ==> " + textfield_namaobat.getText());
        out.println("Kandungan ==> " + textfield_Kandungan.getText());
        out.println("Aturan Pakai ==> " + textfield_Aturan.getText());
        out.println("Efek Samping ==> " + textfield_efek.getText());
        out.println("Tipe ==> " + textfield_tipeobat.getText());
        out.println("Harga ==> " + textfield_hargaobat.getText());
        out.println("Jumlah ==> " + textfield_stockobat.getText());
        out.println("\n\n\n");
        out.println("Total ==> Rp." + textfield_total.getText());

        out.close();  // Step 4
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToRekamMedis(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/rekamMedisForm.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchTolayarBerita(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/layarBerita.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchTolayarPengguna(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/dataPengguna.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}


