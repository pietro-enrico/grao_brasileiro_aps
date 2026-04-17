module app.graobrasileiroaps {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens app to javafx.fxml;
    opens register to javafx.fxml;
    opens about to javafx.fxml;
    opens login to javafx.fxml;

    exports app;
    exports register;
    exports about;
    exports login;
}