package register;

public class RegisterDTO {
    private String email, full_name, cpf, password, confirm_password, is_voluntary;
    private static StringBuilder errors = new StringBuilder();
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        if(full_name.isEmpty()) {
            errors.append("\n- Nome Completo obrigatório.");
        }
        else if(!full_name.matches("^\\p{L}+(\\s+\\p{L}+)*$")) {
            errors.append("\n- Nome Completo inválido");
        }
        else {
            this.full_name = full_name.trim();
        }
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        if(email.isEmpty()) {
            errors.append("\n- Email obrigatório.");
        }
        else if(!email.matches(EMAIL_REGEX)) {
            errors.append("\n- Email inválido");
        }
        else {
            this.email = email.trim().toLowerCase();
        }
    }

    public String getCpf() { return cpf; }

    public void setCpf(String cpf) {
        if(cpf.isEmpty()) {
            errors.append("\n- CPF obrigatório.");
        }
        else if(cpf.length() != 11) {
            errors.append("\n- CPF inválido");
        }
        else if(cpf.matches("^\\\\d+$")) {
            errors.append("\n- CPF inválido. Somente números [11]");
        }
        else {
            this.cpf = cpf.trim();
        }
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        if(password.isEmpty()) {
            errors.append("\n- Senha obrigatória.");
        }
        else if(password.length() < 8) {
            errors.append("\n- Insira uma senha com no mínimo 8 caracteres");
        }
        else {
            this.password = password.trim();
        }
    }

    public String getConfirm_password() { return confirm_password; }

    public void setConfirm_password(String confirm_password) {
        if(confirm_password.isEmpty()) {
            errors.append("\n- Confirmação de Senha obrigatória.");
        }
        else if(!confirm_password.equals(password)) {
            errors.append("\n- Senhas não coincidem");
        }
        else {
            this.confirm_password = confirm_password.trim();
        }
    }

    public Boolean getIs_voluntary() { return RegisterVoluntaryDict.options(is_voluntary); }

    public void setIs_voluntary(String is_voluntary) {
        if(is_voluntary.isEmpty()) {
            errors.append("\n- Voluntário obrigatório.");
        }
        else {
            this.is_voluntary = is_voluntary.trim();
        }
    }

    public static StringBuilder getErrors() { return errors; }

    public static void setErrors(String errors) {
        RegisterDTO.errors = new StringBuilder(errors);
    }
}
