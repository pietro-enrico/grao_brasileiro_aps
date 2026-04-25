package my_donates;

import components.Components;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class MyDonatesController extends Components {

    @FXML private VBox cardsContainer;
    @FXML private ScrollPane scrollPane;
    @FXML private Label lblVazio;

    // Formatador de data — ajuste o padrão conforme o que vem do banco
    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void initialize() {
        List<Map<String, Object>> doacoes = MyDonatesService.getDonatesUserById();

        if (doacoes.isEmpty()) {
            lblVazio.setVisible(true);
            return;
        }

        for (Map<String, Object> doacao : doacoes) {
            cardsContainer.getChildren().add(criarCard(doacao));
        }
    }

    // ─────────────────────────────────────────────
    // Monta um card para cada doação
    // ─────────────────────────────────────────────
    private Pane criarCard(Map<String, Object> doacao) {

        // --- dados ---
        String category    = getString(doacao, "category");
        String subCategory = getString(doacao, "sub_category");
        Object quantityObj = doacao.get("quantity");
        String unity       = getString(doacao, "unity");
        float  value       = getFloat(doacao, "value_payment");
        String method      = getString(doacao, "method_value_payment");
        boolean sent       = (boolean) doacao.getOrDefault("donation_was_sent", false);
        String date        = formatarData(doacao.get("date_donation"));

        // --- card container ---
        Pane card = new Pane();
        card.setPrefHeight(110.0);
        card.setPrefWidth(1080.0);
        card.setStyle("-fx-background-color: #3A2A1A; -fx-background-radius: 10;");

        // --- coluna: Data ---
        Label lblDataTitulo = labelTitulo("Data da doação:");
        lblDataTitulo.setLayoutX(57); lblDataTitulo.setLayoutY(18);

        Label lblDataValor = labelValor(date);
        lblDataValor.setLayoutX(57); lblDataValor.setLayoutY(50);

        // --- coluna: Categoria/Suprimento ou Valor ---
        boolean isDinheiro = category.equalsIgnoreCase("dinheiro");

        Label lblCatTitulo, lblCatValor;
        if (isDinheiro) {
            lblCatTitulo = labelTitulo("Valor:");
            lblCatValor  = labelValor("R$ " + String.format("%.2f", value));
        } else {
            lblCatTitulo = labelTitulo("Suprimento:");
            lblCatValor  = labelValor(subCategory.isEmpty() ? category : subCategory);
        }
        lblCatTitulo.setLayoutX(280); lblCatTitulo.setLayoutY(18);
        lblCatValor.setLayoutX(280);  lblCatValor.setLayoutY(50);

        // --- coluna: Quantidade ou Método ---
        Label lblQtdTitulo, lblQtdValor;
        if (isDinheiro) {
            lblQtdTitulo = labelTitulo("Pagamento:");
            lblQtdValor  = labelValor(method.isEmpty() ? "—" : method);
        } else {
            String qtdFormatada = quantityObj != null
                    ? formatarQuantidade((float) quantityObj, unity)
                    : "—";
            lblQtdTitulo = labelTitulo("Quantidade:");
            lblQtdValor  = labelValor(qtdFormatada);
        }
        lblQtdTitulo.setLayoutX(530); lblQtdTitulo.setLayoutY(18);
        lblQtdValor.setLayoutX(530);  lblQtdValor.setLayoutY(50);

        // --- coluna: Status ---
        Label lblStatusTitulo = labelTitulo("Status:");
        lblStatusTitulo.setLayoutX(780); lblStatusTitulo.setLayoutY(18);

        Label lblStatusValor = labelValor(sent ? "✔ Enviado" : "✔ Concluído");
        lblStatusValor.setStyle("-fx-text-fill: #A8C8A0; -fx-font-size: 15px;");
        lblStatusValor.setLayoutX(780); lblStatusValor.setLayoutY(50);

        card.getChildren().addAll(
                lblDataTitulo, lblDataValor,
                lblCatTitulo,  lblCatValor,
                lblQtdTitulo,  lblQtdValor,
                lblStatusTitulo, lblStatusValor
        );

        return card;
    }

    // ─────────────────────────────────────────────
    // Helpers de UI
    // ─────────────────────────────────────────────
    private Label labelTitulo(String texto) {
        Label l = new Label(texto);
        l.setTextFill(javafx.scene.paint.Color.web("#ffe1bf"));
        l.setFont(new Font(18));
        l.setPrefWidth(220);
        return l;
    }

    private Label labelValor(String texto) {
        Label l = new Label(texto);
        l.setTextFill(javafx.scene.paint.Color.web("#ffe1bf"));
        l.setFont(new Font(15));
        l.setPrefWidth(220);
        return l;
    }

    // ─────────────────────────────────────────────
    // Helpers de dados
    // ─────────────────────────────────────────────
    private String getString(Map<String, Object> map, String key) {
        Object v = map.get(key);
        return v != null ? v.toString() : "";
    }

    private float getFloat(Map<String, Object> map, String key) {
        Object v = map.get(key);
        if (v == null) return 0f;
        if (v instanceof Float) return (float) v;
        if (v instanceof Double) return ((Double) v).floatValue();
        try { return Float.parseFloat(v.toString()); } catch (Exception e) { return 0f; }
    }

    private String formatarQuantidade(float qty, String unity) {
        // Remove casas decimais se for número inteiro
        if (qty == (int) qty) {
            return (int) qty + (unity.isEmpty() ? "" : " " + unity);
        }
        return qty + (unity.isEmpty() ? "" : " " + unity);
    }

    private String formatarData(Object dataBanco) {
        if (dataBanco == null) return "—";
        try {
            // Tenta LocalDateTime primeiro (timestamp do SQLite)
            if (dataBanco instanceof LocalDateTime) {
                return ((LocalDateTime) dataBanco).format(FORMATTER);
            }
            // Fallback: parse da string
            String s = dataBanco.toString();
            // SQLite retorna "2026-04-18 00:00:00" ou "2026-04-18T00:00:00"
            s = s.replace("T", " ");
            if (s.length() > 10) s = s.substring(0, 10); // pega só a data
            // s agora é "2026-04-18"
            String[] partes = s.split("-");
            return partes[2] + "/" + partes[1] + "/" + partes[0];
        } catch (Exception e) {
            return dataBanco.toString();
        }
    }
}
