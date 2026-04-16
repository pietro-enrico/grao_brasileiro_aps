package register;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;

public class RegisterController {
    @FXML
    protected TextField full_name, email, cpf;

    @FXML
    protected PasswordField password, confirm_password;

    @FXML
    protected ComboBox is_voluntary;

    @FXML
    protected void OnRegister() {
        System.out.println(email.getText());
    }
}
