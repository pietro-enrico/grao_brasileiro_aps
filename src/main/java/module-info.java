module grao.brasileiro.aps {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires jbcrypt;

    opens app to javafx.fxml;
    opens login to javafx.fxml;
    opens register to javafx.fxml;
    opens donate to javafx.fxml;
    opens collaborators to javafx.fxml;
    opens about to javafx.fxml;
    opens payment to javafx.fxml;
    opens my_donates to javafx.fxml;
    opens components to javafx.fxml;

    exports app;
    exports login;
    exports register;
    exports donate;
    exports collaborators;
    exports about;
    exports payment;
    exports my_donates;
    exports components;
}