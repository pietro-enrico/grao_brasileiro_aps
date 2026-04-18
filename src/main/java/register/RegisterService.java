package register;

import config.database.DatabaseConnection;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterService {
    static final Connection connection = DatabaseConnection.connection();
    static PreparedStatement stmt = null;

    public static Map<String, Object> save(RegisterDTO user) {
        boolean exists = verifyExistsUser(user);
        if(exists) {
            return Map.of(
                    "status", "error",
                    "message", "Usuário já cadastrado",
                    "code", 409
            );
        }
        else if(insertUser(user)){
            return Map.of(
                    "status", "success",
                    "message", "Usuário cadastrado com sucesso",
                    "code", 201
            );
        }
        else {
            return Map.of(
                    "status", "error",
                    "message", "Erro desconhecido",
                    "code", ""
            );
        }
    }

    public static boolean verifyExistsUser(RegisterDTO user)  {
        String sql = "SELECT 1 FROM User WHERE email = ? OR cpf = ? LIMIT 1";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getCpf());
            ResultSet resultSelect = stmt.executeQuery();
            return resultSelect.next();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            if(stmt != null ) {
                try {
                    stmt.close();
                }
                catch (SQLException ignored) {}
            }
        }
    }

    public static boolean insertUser(RegisterDTO user)  {
        String sql = "INSERT INTO User (full_name, email, cpf, password, createdAt, isVoluntary) VALUES(?,?,?,?,?,?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            LocalDateTime now = LocalDateTime.now();
            String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            String cpfFormated = user.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            stmt.setString(1, user.getFull_name());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, cpfFormated);
            stmt.setString(4, hashPassword);
            stmt.setObject(5, now);
            stmt.setBoolean(6, user.getIs_voluntary());
            int executed = stmt.executeUpdate();
            return executed > 0;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        finally {
            if(stmt != null ) {
                try {
                    stmt.close();
                }
                catch (SQLException ignored) {}
            }
        }
    }
}

