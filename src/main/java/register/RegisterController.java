package register;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import message.Message;

public class RegisterController {
    @FXML
    private TextField full_name, email, cpf;

    @FXML
    private PasswordField password, confirm_password;

    @FXML
    private ComboBox<String> is_voluntary;

    @FXML
    protected boolean OnRegister() {
        RegisterDTO dto = new RegisterDTO();
        RegisterDTO.setErrors("");

        dto.setFull_name(full_name.getText());
        dto.setEmail(email.getText());
        dto.setCpf(cpf.getText());
        dto.setIs_voluntary(is_voluntary.getValue());
        dto.setPassword(password.getText());
        dto.setConfirm_password(confirm_password.getText());

        if(!RegisterDTO.getErrors().isEmpty()) {
            Message.showMessage(AlertType.INFORMATION, "Atenção", "Corrija os seguintes erros:", RegisterDTO.getErrors().toString());
            return false;
        }
        return true;
    }

    @FXML
    public void initialize() {
        is_voluntary.getItems().addAll("Quero mudar o mundo!", "Dessa vez não =/");
        is_voluntary.setValue("Quero mudar o mundo!");
    }
}
