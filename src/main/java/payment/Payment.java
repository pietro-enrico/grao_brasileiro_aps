package payment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Payment extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = Logger.getLogger("javafx");
        logger.setLevel(Level.SEVERE);
        FXMLLoader fxmlLoader = new FXMLLoader(Payment.class.getResource("payment-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Doação - Grão Brasileiro");
        stage.setScene(scene);
        stage.show();
    }
}
