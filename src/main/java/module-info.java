module com.example.graobrasileiroaps {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens app to javafx.fxml;
    exports app;
}