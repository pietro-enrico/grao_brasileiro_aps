package register;

import components.Components;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.Login;
import message.Message;
import java.io.IOException;
import java.util.Map;

public class RegisterController extends Components {
    @FXML
    private TextField full_name, email, cpf;

    @FXML
    private PasswordField password, confirm_password;

    @FXML
    private ComboBox<String> is_voluntary;

    @FXML
    public void initialize() {
        is_voluntary.getItems().addAll("Quero mudar o mundo!", "Dessa vez não =/");
        is_voluntary.setValue("Quero mudar o mundo!");
    }

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

        if (!RegisterDTO.getErrors().isEmpty()) {
            Message.showMessage(AlertType.INFORMATION, "Atenção", "Corrija os seguintes erros:", RegisterDTO.getErrors().toString());
            return false;
        }
        else {
            Map<String, Object> register = RegisterService.save(dto);

            if(register.get("status").equals("success") && register.get("code").equals(201)) {
                Message.showMessage(AlertType.CONFIRMATION, "Confirmação", "Usuário cadastrado!", "O usuário foi cadastrado com sucesso. Faça Login agora mesmo e começe a doar!");
                for (TextField field : new TextField[]{full_name, email, cpf}) {
                    field.setText("");
                }
                for (PasswordField field : new PasswordField[]{password, confirm_password}) {
                    field.setText("");
                }
                is_voluntary.setValue("Quero mudar o mundo!");
                return true;
            }
            else if(register.get("status").equals("error") && register.get("code").equals(409)) {
                Message.showMessage(AlertType.ERROR, "Atenção", "Usuário existente", "Já existe um usuário com este email ou CPF!");
                return false;
            }
            else  {
                Message.showMessage(AlertType.ERROR, "Atenção", "Erro no Servidor do Banco de dados", "Erro ao executar a operação no banco de dados. Entre com contato com o suporte da Grão Brasileiro.");
                return false;
            }
        }
    }
 }
