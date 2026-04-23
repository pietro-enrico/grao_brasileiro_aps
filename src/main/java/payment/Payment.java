package payment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.Login;
import user_session.UserSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Payment extends Application {
    public String initialText;

    @Override
    public void start(Stage stage) throws IOException {
        Logger logger = Logger.getLogger("javafx");
        logger.setLevel(Level.SEVERE);

        if (UserSession.getInstance() == null) {
            Login login = new Login();
            login.start(stage);
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(Payment.class.getResource("payment-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Doação - Grão Brasileiro");
        stage.setScene(scene);
        PaymentController controller = fxmlLoader.getController();
        controller.categoria.setValue(initialText);
        stage.show();
    }
}
