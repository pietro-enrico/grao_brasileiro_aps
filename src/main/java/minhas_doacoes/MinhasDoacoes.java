package minhas_doacoes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class MinhasDoacoes extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MinhasDoacoes.class.getResource("minhas_doacoes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Minhas Doações - Grão Brasileiro");
        stage.setScene(scene);
        stage.show();
    }
}