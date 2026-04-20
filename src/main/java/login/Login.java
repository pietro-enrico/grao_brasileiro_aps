package login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = Logger.getLogger("javafx");
        logger.setLevel(Level.SEVERE);
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Login - Grão Brasileiro");
        stage.setScene(scene);
        stage.show();
    }
}
