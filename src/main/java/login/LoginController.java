package login;

import components.Components;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import message.Message;
import user_session.UserSession;
import java.io.IOException;
import java.util.Map;

public class LoginController extends Components {
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    public Button button_login;

    @FXML
    protected boolean onLogin() throws IOException {
        LoginDTO dto = new LoginDTO();
        LoginDTO.setErrors("");
        dto.setEmail(email.getText());
        dto.setPassword(password.getText());

        if(!LoginDTO.getErrors().isEmpty()) {
            Message.showMessage(Alert.AlertType.INFORMATION, "Atenção", "Corrija os seguintes erros para poder realizar login:", LoginDTO.getErrors().toString());
            return false;
        }
        else {
            Map<String, Object> autenticarUsuario = LoginService.login(dto);
            if(autenticarUsuario.get("status").equals("success") && autenticarUsuario.get("code").equals(200)) {
                Message.showMessage(Alert.AlertType.CONFIRMATION, "Confirmação", "Login com sucesso!", "Seu login foi realizado com sucesso! Seja bem-vindo a Grão Brasileiro, faça as suas doações e mude o mundo!");
                email.setText("");
                password.setText("");
                UserSession.startSession((Integer) autenticarUsuario.get("id_user"), autenticarUsuario.get("full_name").toString());
                redirectToAppByLogin(button_login);
                return true;
            }
            else if(autenticarUsuario.get("status").equals("error") && autenticarUsuario.get("code").equals(401)) {
                Message.showMessage(Alert.AlertType.ERROR, "Atenção", "Credenciais inválidas", "Verifique se a senha e/ou email estão corretos e tente novamente!");
                return false;
            }
            else  {
                Message.showMessage(Alert.AlertType.ERROR, "Atenção", "Erro no Servidor do Banco de dados", "Erro ao executar a operação no banco de dados. Entre com contato com o suporte da Grão Brasileiro.");
                return false;
            }
        }
    }
}
