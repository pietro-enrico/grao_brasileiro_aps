module app.graobrasileiroaps {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;

    opens app to javafx.fxml;
    opens register to javafx.fxml;
    opens collaborators to javafx.fxml;
    opens about to javafx.fxml;
    opens login to javafx.fxml;

    exports collaborators;
    exports app;
    exports login;
    exports register;
    exports about;
}