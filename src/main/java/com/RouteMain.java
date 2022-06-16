package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RouteMain extends Application{
    public static void main(String[] args) {
        System.out.println(RouteMain.class.getResource("admin/berita.fxml"));
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RouteMain.class.getResource("pengguna/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1450, 740);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
