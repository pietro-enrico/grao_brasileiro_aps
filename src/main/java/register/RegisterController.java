package register;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;

public class RegisterController {
    @FXML
    private TextField full_name, email, cpf;

    @FXML
    private PasswordField password, confirm_password;

    @FXML
    private ComboBox<String> is_voluntary;

    @FXML
    protected void OnRegister() {
        RegisterDTO dto = new RegisterDTO();
        dto.setIs_voluntary(is_voluntary.getValue());
        System.out.println(dto.getIs_voluntary());
    }

    @FXML
    public void initialize() {
        is_voluntary.getItems().addAll("Quero mudar o mundo!", "Dessa vez não =/");
        is_voluntary.setValue("Quero mudar o mundo!");
    }
}
