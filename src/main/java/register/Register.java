package register;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Register extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Register.class.getResource("register-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Cadastro de Usuário - Grão Brasileiro");
        stage.setScene(scene);
        stage.show();
        scene.getStylesheets().add(getClass().getResource("/css/combo_box.css").toExternalForm());
    }
}
