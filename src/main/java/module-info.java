module com.example.graobrasileiroaps {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.graobrasileiroaps to javafx.fxml;
    exports com.example.graobrasileiroaps;
}