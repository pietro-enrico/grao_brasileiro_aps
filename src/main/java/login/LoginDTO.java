package login;

import register.RegisterDTO;

public class LoginDTO {
    private String email, password;
    private static StringBuilder errors = new StringBuilder();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            errors.append("\n- Campo de e-mail é obrigátorio");
        } else if (!email.matches(RegisterDTO.EMAIL_REGEX)) {
            errors.append("\n- Email inválido");
        } else {
            this.email = email.trim().toLowerCase();
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.isEmpty()) {
            errors.append("\n- Campo de senha é obrigátorio");
        } else {
            this.password = password.trim();
        }
    }

    public static StringBuilder getErrors() {
        return errors;
    }

    public static void setErrors(String errors) {
        LoginDTO.errors = new StringBuilder(errors);
    }
}
