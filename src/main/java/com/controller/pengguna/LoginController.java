package com.controller.pengguna;

import com.model.users.Users;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class LoginController {
    private Users dataUsers;
    private Stage stage;
    private Scene scene;

    public LoginController() throws SQLException, ClassNotFoundException {
        dataUsers = new Users(
                0,
                "",
                "",
                "",
                "",
                ""
        );
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void switchToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/register.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;

    public void switchToMenu(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        System.out.println(dataUsers.login(email, password));
        if (dataUsers.login(email, password)==2) {
            Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/pengguna/home.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (dataUsers.login(email, password)==1) {
            Parent root = FXMLLoader.load(new URL("file:/D:/Java/BelajarJava/Kuliah/RuxneKesMas/target/classes/com/admin/user.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            System.out.println("error");
        }
    }



    public void register(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();
        System.out.println(password + email);
    }

}
