package com.controller.admin;

import com.MVC.Controller;
import com.model.object.RekamMedis;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

public class RekamMedisController extends Controller {
    RekamMedis rekamMedisData;
    ObservableList<RekamMedis> listRekamMedis;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField textfield_IDdokter;

    @FXML
    private TextField textfield_IDpasien;

    @FXML
    private TextField textfield_diagnosaAkhir;

    @FXML
    private TextField textfield_diagnosaAwal;

    @FXML
    private TextField textfield_id;

    @FXML
    private TextField textfield_listobat;

    @FXML
    private TableView<RekamMedis> tabel_rekamMedis;

    @FXML
    private TableColumn<RekamMedis, Integer> tabel_IDpasien;

    @FXML
    private TableColumn<RekamMedis, String> tabel_ListObat;

    @FXML
    private TableColumn<RekamMedis, Integer> tabel_IDdokter;

    @FXML
    private TableColumn<RekamMedis, String> tabel_diagnosaAkhir;

    @FXML
    private TableColumn<RekamMedis, String> tabel_diagnosaAwal;

    @FXML
    private TableColumn<RekamMedis, Integer> tabel_id;

    @FXML
    private TableColumn<RekamMedis, String> tabel_tindakan;

    @FXML
    private TextField textfield_tindakan;

    public RekamMedisController() throws SQLException, ClassNotFoundException {
        ArrayList<String> nullListObat = new ArrayList<>();
        nullListObat.add("");
        rekamMedisData = new RekamMedis(
                0,
                "",
                "",
                "",
                0,
                0,
                nullListObat
        );
        this.listRekamMedis = FXCollections.observableList(rekamMedisData.all());

    }
    public void Show(){
        tabel_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabel_diagnosaAwal.setCellValueFactory(new PropertyValueFactory<>("diagnosaAwal"));
        tabel_diagnosaAkhir.setCellValueFactory(new PropertyValueFactory<>("diagnosaAkhir"));
        tabel_IDpasien.setCellValueFactory(new PropertyValueFactory<>("idPasien"));
        tabel_tindakan.setCellValueFactory(new PropertyValueFactory<>("tindakan"));
        tabel_ListObat.setCellValueFactory(new PropertyValueFactory<>("listObat"));
        tabel_IDdokter.setCellValueFactory(new PropertyValueFactory<>("idDokter"));
        tabel_rekamMedis.setItems(this.listRekamMedis);

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
        ArrayList<String> tempListObat = new ArrayList<>(Arrays.asList(textfield_listobat.getText().split(",")));
        RekamMedis tempRekamMedis = new RekamMedis(
                id,
                textfield_diagnosaAwal.getText(),
                textfield_diagnosaAkhir.getText(),
                textfield_tindakan.getText(),
                Integer.parseInt(textfield_IDdokter.getText()),
                Integer.parseInt(textfield_IDpasien.getText()),
                tempListObat
        );
        rekamMedisData.create(tempRekamMedis);
        this.Refresh(event);
    }

    @FXML
    public void Delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        rekamMedisData.delete(Integer.parseInt(textfield_id.getText()));
        textfield_id.setText("");
        textfield_diagnosaAwal.setText("");
        textfield_diagnosaAkhir.setText("");
        textfield_tindakan.setText("");
        textfield_IDdokter.setText("");
        textfield_IDpasien.setText("");
        textfield_listobat.setText("");
        this.Refresh(event);
    }

    @FXML
    public void Pilih(ActionEvent event) {
        RekamMedis rekamMedis = tabel_rekamMedis.getSelectionModel().getSelectedItem();
        if(rekamMedis != null){
            textfield_id.setText(String.valueOf(rekamMedis.getId()));
            textfield_diagnosaAwal.setText(rekamMedis.getDiagnosaAwal());
            textfield_diagnosaAkhir.setText(rekamMedis.getDiagnosaAkhir());
            textfield_tindakan.setText(rekamMedis.getTindakan());
            textfield_IDdokter.setText(String.valueOf(rekamMedis.getIdDokter()));
            textfield_IDpasien.setText(String.valueOf(rekamMedis.getIdPasien()));
            textfield_listobat.setText(String.valueOf(rekamMedis.getListObat()));
        }
    }

    @FXML
    public void Refresh(ActionEvent event) throws SQLException, ClassNotFoundException {
        this.listRekamMedis = FXCollections.observableList(rekamMedisData.all());
        this.tabel_rekamMedis.getItems().clear();
        this.Show();
    }

    @FXML
    public void Edit(ActionEvent event) throws SQLException, ClassNotFoundException {
        ArrayList<String> tempListObat = new ArrayList<>(Arrays.asList(textfield_listobat.getText().split(",")));
        RekamMedis tempBerita = new RekamMedis(
                Integer.parseInt(textfield_id.getText()),
                textfield_diagnosaAwal.getText(),
                textfield_diagnosaAkhir.getText(),
                textfield_tindakan.getText(),
                Integer.parseInt(textfield_IDdokter.getText()),
                Integer.parseInt(textfield_IDpasien.getText()),
                tempListObat
        );
        rekamMedisData.update(tempBerita,Integer.parseInt(textfield_id.getText()));
        this.Refresh(event);
    }
}
