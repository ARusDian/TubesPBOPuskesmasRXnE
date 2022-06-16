package com.controller.admin;

import com.MVC.Controller;
import com.model.users.Users;
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

public class UserController extends Controller {
    private ObservableList<Users> listUser;
    private Users dataUsers;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField tetxtfield_id;

    @FXML
    private TextField tetxtfield_email;

    @FXML
    private TextField textfield_nomerhp;

    @FXML
    private TextField tetxtfield_password;

    @FXML
    private TextField tetxtfield_role;

    @FXML
    private TableView<Users> tabelUser;

    @FXML
    private TableColumn<Users, String> tabel_email;

    @FXML
    private TableColumn<Users, Integer> tabel_id;

    @FXML
    private TableColumn<Users, String> tabel_nohp;

    @FXML
    private TableColumn<Users, String> tabel_password;

    @FXML
    private TableColumn<Users, String> tabel_role;

    @FXML
    private TableColumn<Users, String> tabel_username;

    @FXML
    private TextField tetxtfield_username;

    public UserController() throws SQLException, ClassNotFoundException {
        dataUsers = new Users(
                0,
                "",
                "",
                "",
                "",
                ""
        );
        this.listUser = FXCollections.observableList(dataUsers.all());

    }
    public void Show(){
        tabel_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabel_username.setCellValueFactory(new PropertyValueFactory<>("name"));
        tabel_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabel_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        tabel_role.setCellValueFactory(new PropertyValueFactory<>("role"));
        tabel_nohp.setCellValueFactory(new PropertyValueFactory<>("noHp"));
        tabelUser.setItems(this.listUser);

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
        System.out.println(tetxtfield_id.getText());
        int id = (!Objects.equals(tetxtfield_id.getText(), "")) ? Integer.parseInt(tetxtfield_id.getText()) : 0;
        Users tempUser = new Users(
                id,
                tetxtfield_username.getText(),
                tetxtfield_email.getText(),
                tetxtfield_password.getText(),
                tetxtfield_role.getText(),
                textfield_nomerhp.getText()
        );
        dataUsers.create(tempUser);
        this.Refresh(event);
    }

    @FXML
    public void Delete(ActionEvent event) throws SQLException, ClassNotFoundException {
        dataUsers.delete(Integer.parseInt(tetxtfield_id.getText()));
        tetxtfield_id.setText("");
        tetxtfield_username.setText("");
        tetxtfield_email.setText("");
        tetxtfield_password.setText("");
        tetxtfield_role.setText("");
        textfield_nomerhp.setText("");
        this.Refresh(event);
    }

    @FXML
    public void Pilih(ActionEvent event) {
        Users tempUser = tabelUser.getSelectionModel().getSelectedItem();
        if(tempUser != null){
            tetxtfield_id.setText(String.valueOf(tempUser.getId()));
            tetxtfield_username.setText(String.valueOf(tempUser.getName()));
            tetxtfield_email.setText(tempUser.getEmail());
            tetxtfield_password.setText(tempUser.getPassword());
            tetxtfield_role.setText(tempUser.getRole());
            textfield_nomerhp.setText(tempUser.getNoHp());
        }

    }

    @FXML
    public void Refresh(ActionEvent event) throws SQLException, ClassNotFoundException {
        this.listUser = FXCollections.observableList(dataUsers.all());
        this.tabelUser.getItems().clear();
        this.Show();
    }

    @FXML
    public void Edit(ActionEvent event) throws SQLException, ClassNotFoundException {
        Users tempUser = new Users(
                Integer.parseInt(tetxtfield_id.getText()),
                tetxtfield_username.getText(),
                tetxtfield_email.getText(),
                tetxtfield_password.getText(),
                tetxtfield_role.getText(),
                textfield_nomerhp.getText()
        );
        dataUsers.update(tempUser,Integer.parseInt(tetxtfield_id.getText()));
        this.Refresh(event);
    }
}
