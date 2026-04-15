module app.graobrasileiroaps {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens app to javafx.fxml;
    opens register to javafx.fxml;
    exports app;
    exports register;
}