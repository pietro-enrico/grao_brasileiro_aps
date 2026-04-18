package register;

import javafx.scene.control.Alert;
import message.Message;

public class RegisterDTO {
    private String email, full_name, cpf, password, confirm_password, is_voluntary;
    private static int errors = 0;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        if(full_name.isEmpty()) {
            errors++;
        }
        else {
            this.full_name = full_name.trim();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(email.isEmpty()) {
            errors++;
        } else {
                if(!email.contains("@")) {
                    Message.showMessage(Alert.AlertType.INFORMATION, "Atenção", "Email inválido", "Insira um email válido para prosseguir com o seu cadastro. Exemplo: seuemail@teste.com");
                    return;
            }
            this.email = email.trim();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf.isEmpty()) {
            errors++;
        } else {
            this.cpf = cpf.trim();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.isEmpty()) {
            errors++;
        } else {
            this.password = password.trim();
        }
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        if(confirm_password.isEmpty()) {
            errors++;
        } else {
            if(!confirm_password.equals(password)) {
                Message.showMessage(Alert.AlertType.INFORMATION, "Atenção", "Senhas não coincidem", "Por favor, verifique as senhas inseridas.");
                return;
            }
            this.confirm_password = confirm_password.trim();
        }
    }

    public Integer getIs_voluntary() {
        return RegisterVoluntaryDict.options(is_voluntary);
    }

    public void setIs_voluntary(String is_voluntary) {
        if(is_voluntary.isEmpty()) {
            errors++;
        }
        else {
            this.is_voluntary = is_voluntary.trim();
        }
    }

    public static boolean getErrors() {
        return errors > 0;
    }

    public static void setErrors(int errors) {
        RegisterDTO.errors = errors;
    }
}
