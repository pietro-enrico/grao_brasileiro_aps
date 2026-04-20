package payment;

import config.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

public class PaymentService {
    static final Connection connection = DatabaseConnection.connection();
    static PreparedStatement stmt = null;

    public static Map<String, Object> insertDonate(PaymentDTO dto) {
        return Map.of();
    }
}
