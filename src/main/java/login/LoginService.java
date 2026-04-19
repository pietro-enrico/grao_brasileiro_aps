package login;

import config.database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

public class LoginService {
    static final Connection connection = DatabaseConnection.connection();
    static PreparedStatement stmt = null;

    public static Map<String, Object> login(LoginDTO user) {
        Map<String, Object> exists = LoginService.verifyExistsUser(user);

        if (exists.isEmpty()) {
            return Map.of(
                    "status", "error",
                    "message", "Credenciais inválidas",
                    "code", 401
            );
        } else {
            boolean userValidated = validatePassword(user, exists);
            if (userValidated) {
                return Map.of(
                        "status", "success",
                        "message", "Login realizado com sucesso",
                        "code", 200
                );
            }
            else {
                return Map.of(
                        "status", "error",
                        "message", "Credenciais inválidas",
                        "code", 401
                );
            }
        }
    }

    public static Map<String, Object> verifyExistsUser(LoginDTO user) {
        String sql = "SELECT id_user, password FROM User WHERE email = ? LIMIT 1";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            ResultSet resultSelect = stmt.executeQuery();
            if (resultSelect.next()) {
                return Map.of(
                        "id_user", resultSelect.getInt("id_user"),
                        "password", resultSelect.getString("password")
                );
            } else {
                return Map.of();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return Map.of();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    public static boolean validatePassword(LoginDTO user, Map<String, Object> userDb) {
        try {
            String password = user.getPassword();
            String hashPassword = userDb.get("password").toString();
            return BCrypt.checkpw(password, hashPassword);
        }
        catch (Error e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}