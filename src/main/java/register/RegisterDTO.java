package register;

import java.io.IOException;

public class RegisterDTO {
    private String email, full_name, cpf, password, confirm_password, is_voluntary;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public Integer getIs_voluntary() {
        RegisterVoluntaryDict registerVoluntaryDict = new RegisterVoluntaryDict();
        return registerVoluntaryDict.options(is_voluntary);
    }

    public void setIs_voluntary(String is_voluntary) {
        this.is_voluntary = is_voluntary;
    }
}
