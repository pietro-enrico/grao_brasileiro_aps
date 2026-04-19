package login;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import register.Register;
import java.io.IOException;

public class LoginController {
    @FXML
    public void clickToRegister(MouseEvent event) throws IOException {
        Register register = new Register();
        Stage stage = new Stage();
        register.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }
}
