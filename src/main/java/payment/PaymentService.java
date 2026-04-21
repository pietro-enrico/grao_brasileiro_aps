package payment;

import config.database.DatabaseConnection;
import user_session.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Map;

public class PaymentService {
    static final Connection connection = DatabaseConnection.connection();
    static PreparedStatement stmt = null;

    public static Map<String, Object> insertDonate(PaymentDTO donate) {
        String sql = "INSERT INTO Donate (category, sub_category, quantity, value_payment, method_value_payment, date_donation, fk_id_user) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement stmt = connection.prepareStatement(sql)) {
            LocalDateTime now = LocalDateTime.now();
            int id_user = UserSession.getInstance().getId_user();

            stmt.setString(1, donate.getCategoria());
            stmt.setString(2, donate.getSubCategoria());
            stmt.setString(3, donate.getQuantidade());
            stmt.setFloat(4, donate.getValor());
            stmt.setString(5, donate.getTipoPagamento());
            stmt.setObject(6, now);
            stmt.setInt(7, id_user);
            int executed_success = stmt.executeUpdate();

            if(executed_success > 0) {
                return Map.of(
                        "status", "success",
                        "message", "Doação realizada com sucesso! Você contribuiu para que o mundo seja melhor!!",
                        "code", 201
                );
            }
            else {
                return Map.of(
                        "status", "error",
                        "message", "Erro no servidor do banco de dados.",
                        "code", 500
                        );
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return Map.of(
                    "status", "error",
                    "message", e.getMessage(),
                    "code", 500
            );
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
