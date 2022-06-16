package com.controller.pengguna;

import com.MVC.Controller;
import com.model.object.RekamMedis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RekamMedisFormController extends Controller {
    private RekamMedis rekamMedisData;
    private ObservableList<RekamMedis> listRekamMedis;

    @FXML
    private TableColumn<RekamMedis, String> col__diagnosa;

    @FXML
    private TableColumn<RekamMedis, String> col_keluhan;

    @FXML
    private TableColumn<RekamMedis, String> col_obat;

    @FXML
    private TableColumn<RekamMedis, String> col_tanggal;

    @FXML
    private TableColumn<RekamMedis, String> col_tindakan;


    @FXML
    private TableView<RekamMedis> tabelRekamMedis;

    RekamMedisFormController() throws SQLException, ClassNotFoundException {
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
        listRekamMedis = FXCollections.observableArrayList(rekamMedisData.filter("mido"));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Show();
    }
    public void Show(){
        col__diagnosa.setCellValueFactory(new PropertyValueFactory<>("diagnosa"));
        col_keluhan.setCellValueFactory(new PropertyValueFactory<>("keluhan"));
        col_obat.setCellValueFactory(new PropertyValueFactory<>("obat"));
        col_tanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        col_tindakan.setCellValueFactory(new PropertyValueFactory<>("tindakan"));
        tabelRekamMedis.setItems(listRekamMedis);
    }
}
