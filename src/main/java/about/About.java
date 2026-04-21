package about;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.Login;
import user_session.UserSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class About extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = Logger.getLogger("javafx");
        logger.setLevel(Level.SEVERE);

        if(UserSession.getInstance() == null) {
            Login login = new Login();
            login.start(stage);
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(About.class.getResource("about-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Sobre - Grão Brasileiro");
        stage.setScene(scene);
        stage.show();
    }
}
