module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.java;


    opens com.admin to javafx.fxml;
    opens com.model.object to javafx.fxml;
    opens com.model.users to javafx.fxml;
    opens com.MVC to javafx.fxml;
    opens com to javafx.graphics;
    opens com.controller.admin to javafx.fxml;
    opens com.controller.pengguna to javafx.fxml;
    exports com;
    exports com.model.object;
    exports com.model.users;
    exports com.controller.admin;
    exports com.MVC;
    exports com.controller.pengguna;

}