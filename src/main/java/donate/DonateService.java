package donate;

import config.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class DonateService {
    static final Connection connection = DatabaseConnection.connection();
    static PreparedStatement stmt = null;

    public static Map<String, Object> listDataCharts() {
        String sqlDataCharts = """
                SELECT
                     SUM(CASE WHEN (category = 'Dinheiro' AND unity = 'R$' AND method_value_payment IS NOT NULL) THEN value_payment ELSE 0 END) AS chart_value_money_general,
                     SUM(CASE WHEN (category = 'Dinheiro' AND unity = 'R$' AND method_value_payment IS NOT NULL AND (date_donation >= date('now', 'start of month') AND date_donation < date('now', 'start of month', '+1 month'))) THEN value_payment ELSE 0 END) AS chart_value_money_per_week,
                     SUM(CASE WHEN (category = 'Dinheiro' AND unity = 'R$' AND method_value_payment IS NOT NULL AND (date_donation >= date('now', '-7 days') AND date_donation < date('now', '+1 day'))) THEN value_payment ELSE 0 END) AS chart_value_money_per_month,
                     SUM(CASE WHEN (category = 'Alimento' AND unity = 'Kg' AND sub_category IS NOT NULL) THEN quantity ELSE 0 END) AS chart_value_food_general,
                     SUM(CASE WHEN (category = 'Bebida' AND unity = 'L' AND sub_category IS NOT NULL) THEN quantity ELSE 0 END) AS chart_value_drink_general,
                     SUM(CASE WHEN (category = 'Alimento' AND sub_category = 'Arroz' AND unity = 'Kg') THEN quantity ELSE 0 END) AS chart_value_food_rice,
                     SUM(CASE WHEN (category = 'Alimento' AND sub_category = 'Feijão' AND unity = 'Kg') THEN quantity ELSE 0 END) AS chart_value_food_bean,
                     SUM(CASE WHEN (category = 'Alimento' AND sub_category = 'Macarrão' AND unity = 'Kg') THEN quantity ELSE 0 END) AS chart_value_food_pasta,
                     SUM(CASE WHEN (category = 'Alimento' AND sub_category = 'Óleo' AND unity = 'Kg') THEN quantity ELSE 0 END) AS chart_value_food_oil,
                     SUM(CASE WHEN (category = 'Alimento' AND sub_category = 'Açúcar' AND unity = 'Kg') THEN quantity ELSE 0 END) AS chart_value_food_sugar,
                     SUM(CASE WHEN (category = 'Alimento' AND sub_category = 'Farinha' AND unity = 'Kg') THEN quantity ELSE 0 END) AS chart_value_food_flour,
                     SUM(CASE WHEN (category = 'Bebida' AND sub_category = 'Leite' AND unity = 'L') THEN quantity ELSE 0 END) AS chart_value_drink_milk,
                     SUM(CASE WHEN (category = 'Bebida' AND sub_category = 'Suco' AND unity = 'L') THEN quantity ELSE 0 END) AS chart_value_drink_juice,
                     SUM(CASE WHEN (category = 'Bebida' AND sub_category = 'Água mineral' AND unity = 'L') THEN quantity ELSE 0 END) AS chart_value_drink_water,
                     SUM(CASE WHEN (category = 'Bebida' AND sub_category = 'Achocolatado' AND unity = 'L') THEN quantity ELSE 0 END) AS chart_value_drink_chocolate_milk,
                     SUM(CASE WHEN (category = 'Bebida' AND sub_category = 'Café' AND unity = 'L') THEN quantity ELSE 0 END) AS chart_value_drink_coffee,
                     SUM(CASE WHEN (category = 'Bebida' AND sub_category = 'Chá' AND unity = 'L') THEN quantity ELSE 0 END) AS chart_value_drink_tea
                FROM Donate
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sqlDataCharts)) {
            ResultSet resultSet = stmt.executeQuery();

            if (!resultSet.next()) {
                return Map.of();
            }

            return Map.ofEntries(
                    Map.entry("chart_value_money_general", resultSet.getFloat("chart_value_money_general")),
                    Map.entry("chart_value_money_per_week", resultSet.getFloat("chart_value_money_per_week")),
                    Map.entry("chart_value_money_per_month", resultSet.getFloat("chart_value_money_per_month")),
                    Map.entry("chart_value_food_general", resultSet.getFloat("chart_value_food_general")),
                    Map.entry("chart_value_drink_general", resultSet.getFloat("chart_value_drink_general")),
                    Map.entry("chart_value_food_rice", resultSet.getFloat("chart_value_food_rice")),
                    Map.entry("chart_value_food_bean", resultSet.getFloat("chart_value_food_bean")),
                    Map.entry("chart_value_food_pasta", resultSet.getFloat("chart_value_food_pasta")),
                    Map.entry("chart_value_food_oil", resultSet.getFloat("chart_value_food_oil")),
                    Map.entry("chart_value_food_sugar", resultSet.getFloat("chart_value_food_sugar")),
                    Map.entry("chart_value_food_flour", resultSet.getFloat("chart_value_food_flour")),
                    Map.entry("chart_value_drink_milk", resultSet.getFloat("chart_value_drink_milk")),
                    Map.entry("chart_value_drink_juice", resultSet.getFloat("chart_value_drink_juice")),
                    Map.entry("chart_value_drink_water", resultSet.getFloat("chart_value_drink_water")),
                    Map.entry("chart_value_drink_chocolate_milk", resultSet.getFloat("chart_value_drink_chocolate_milk")),
                    Map.entry("chart_value_drink_coffee", resultSet.getFloat("chart_value_drink_coffee")),
                    Map.entry("chart_value_drink_tea", resultSet.getFloat("chart_value_drink_tea"))
            );

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

}
