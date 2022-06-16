package com.MVC;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public abstract class Controller implements Initializable {
    protected Stage stage;
    protected Scene scene;

    public void Show() {};

    @FXML
    protected void Insert(ActionEvent event) throws SQLException, ClassNotFoundException {};

    @FXML
    protected void Edit(ActionEvent event) throws SQLException, ClassNotFoundException {};

    @FXML
    protected void Delete(ActionEvent event) throws SQLException, ClassNotFoundException {};

    @FXML
    protected void Pilih(ActionEvent event) {};

    @FXML
    protected void Refresh(ActionEvent event) throws SQLException, ClassNotFoundException {};
}
