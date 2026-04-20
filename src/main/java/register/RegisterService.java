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
        String sql = "INSERT INTO User (full_name, email, cpf, password, createdAt, isVoluntary, fk_id_company) VALUES(?,?,?,?,?,?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            LocalDateTime now = LocalDateTime.now();
            String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
            String cpfFormated = user.getCpf().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
            String id_company = user.getIs_voluntary() ? searchCompanyOng() : null;

            stmt.setString(1, user.getFull_name());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, cpfFormated);
            stmt.setString(4, hashPassword);
            stmt.setObject(5, now);
            stmt.setBoolean(6, user.getIs_voluntary());
            stmt.setString(7, id_company);
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

    public static String searchCompanyOng() {
        String sql = "SELECT cnpj FROM CompanyOng WHERE cnpj = ? OR name = ? LIMIT 1";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, "73.884.979/0001-81");
            stmt.setString(2, "Grão Brasileiro");
            ResultSet resultSelect = stmt.executeQuery();

            if(resultSelect.next()) {
                return resultSelect.getString("cnpj");
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
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

