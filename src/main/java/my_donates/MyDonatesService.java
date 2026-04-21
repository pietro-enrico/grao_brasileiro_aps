package my_donates;

import config.database.DatabaseConnection;
import user_session.UserSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyDonatesService {
    static final Connection connection = DatabaseConnection.connection();
    static PreparedStatement stmt = null;

    public static List<Map<String, Object>> getDonatesUserById() {
        String sql = "SELECT id_donate, category, sub_category, quantity, unity, value_payment, method_value_payment, donation_was_sent, date_donation FROM Donate WHERE fk_id_user = ? LIMIT 100";
        List<Map<String, Object>> listaDeDados = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, UserSession.getInstance().getId_user());
            stmt.setFetchSize(100);
            ResultSet resultSelect = stmt.executeQuery();

            while (resultSelect.next()) {
                Map<String, Object> linha = Map.of(
                        "id_donate", resultSelect.getInt("id_donate"),
                        "category", resultSelect.getString("category"),
                        "sub_category", resultSelect.getString("sub_category") != null ? resultSelect.getString("sub_category") : "",
                        "quantity", resultSelect.getString("quantity") != null ? resultSelect.getFloat("quantity") : 0,
                        "unity", resultSelect.getString("unity") != null ? resultSelect.getString("unity") : "",
                        "value_payment", resultSelect.getFloat("value_payment"),
                        "method_value_payment", resultSelect.getString("method_value_payment"),
                        "donation_was_sent", resultSelect.getBoolean("donation_was_sent"),
                        "date_donation", resultSelect.getObject("date_donation")
                );

                listaDeDados.add(linha);
            }
            return listaDeDados;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return List.of();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }
}
