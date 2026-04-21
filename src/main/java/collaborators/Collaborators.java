package collaborators;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.Login;
import user_session.UserSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Collaborators extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = Logger.getLogger("javafx");
        logger.setLevel(Level.SEVERE);

        if(UserSession.getInstance() == null) {
            Login login = new Login();
            login.start(stage);
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Collaborators.class.getResource("collaborators-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Colaboradores - Grão Brasileiro");
        stage.setScene(scene);
        stage.show();
    }
}
