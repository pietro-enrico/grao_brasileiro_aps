package donate;

import components.Components;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class DonateController extends Components implements Initializable {

    // ===== SLIDES =====
    @FXML
    private Pane slide1, slide2, slide3;

    // ===== GRÁFICOS =====
    @FXML
    private BarChart<String, Number> chart1, chart2, chart3;

    // ===== BARRAS DE PROGRESSO =====
    @FXML
    private Rectangle progress1, progress2, progress3;

    private java.util.List<Pane> slides;
    private int currentIndex = 0;

    // Largura total da barra de progresso no FXML
    private static final double PROGRESS_MAX_WIDTH = 500.0;

    @FXML
    private Button Bebida, Alimento, Dinheiro;

    @FXML
    private Label meta_alimento, meta_bebida, meta_dinheiro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        slides = List.of(slide1, slide2, slide3);
        carregarDados();
        mostrarSlide(0);
    }

    // =============================================
    // CARREGAR DADOS NOS GRÁFICOS
    // Substitua os valores fixos pela chamada ao seu banco/serviço
    // =============================================
    private void carregarDados() {
        Map<String, Object> chartDataDonates = DonateService.listDataCharts();
        if (chartDataDonates.isEmpty()) return;

        // --- SLIDE 1: Bebidas ---
        XYChart.Series<String, Number> serie1 = new XYChart.Series<>();
        serie1.setName("Bebidas");
        serie1.getData().add(new XYChart.Data<>("Leite", transformarParaFloat(chartDataDonates.get("chart_value_drink_milk"))));
        serie1.getData().add(new XYChart.Data<>("Suco", transformarParaFloat(chartDataDonates.get("chart_value_drink_juice"))));
        serie1.getData().add(new XYChart.Data<>("Água mineral", transformarParaFloat(chartDataDonates.get("chart_value_drink_water"))));
        serie1.getData().add(new XYChart.Data<>("Achocolatado", transformarParaFloat(chartDataDonates.get("chart_value_drink_chocolate_milk"))));
        serie1.getData().add(new XYChart.Data<>("Café", transformarParaFloat(chartDataDonates.get("chart_value_drink_coffee"))));
        serie1.getData().add(new XYChart.Data<>("Chá", transformarParaFloat(chartDataDonates.get("chart_value_drink_tea"))));
        chart1.getData().add(serie1);
        chart1.setLegendVisible(false);
        estilizarGrafico(chart1);
        atualizarProgresso(progress1, meta_bebida, "litros", transformarParaFloat(chartDataDonates.get("chart_value_drink_general")), 2500);

        // --- SLIDE 2: Alimentos ---
        XYChart.Series<String, Number> serie2 = new XYChart.Series<>();
        serie2.setName("Alimentos");
        serie2.getData().add(new XYChart.Data<>("Arroz", transformarParaFloat(chartDataDonates.get("chart_value_food_rice"))));
        serie2.getData().add(new XYChart.Data<>("Feijão", transformarParaFloat(chartDataDonates.get("chart_value_food_bean"))));
        serie2.getData().add(new XYChart.Data<>("Macarrão", transformarParaFloat(chartDataDonates.get("chart_value_food_pasta"))));
        serie2.getData().add(new XYChart.Data<>("Óleo", transformarParaFloat(chartDataDonates.get("chart_value_food_oil"))));
        serie2.getData().add(new XYChart.Data<>("Açúcar", transformarParaFloat(chartDataDonates.get("chart_value_food_sugar"))));
        serie2.getData().add(new XYChart.Data<>("Farinha", transformarParaFloat(chartDataDonates.get("chart_value_food_flour"))));
        chart2.getData().add(serie2);
        chart2.setLegendVisible(false);
        estilizarGrafico(chart2);
        atualizarProgresso(progress2, meta_alimento, "Kg", transformarParaFloat(chartDataDonates.get("chart_value_food_general")), 5000);

        // --- SLIDE 3: Dinheiro ---
        XYChart.Series<String, Number> serie3 = new XYChart.Series<>();
        serie3.setName("Dinheiro");
        serie3.getData().add(new XYChart.Data<>("Total", transformarParaFloat(chartDataDonates.get("chart_value_money_general"))));
        serie3.getData().add(new XYChart.Data<>("Este mês", transformarParaFloat(chartDataDonates.get("chart_value_money_per_week"))));
        serie3.getData().add(new XYChart.Data<>("Esta semana", transformarParaFloat(chartDataDonates.get("chart_value_money_per_month"))));
        chart3.getData().add(serie3);
        chart3.setLegendVisible(false);
        estilizarGrafico(chart3);
        atualizarProgresso(progress3, meta_dinheiro, "R$", transformarParaFloat(chartDataDonates.get("chart_value_money_general")), 10000);
    }

    // Aplica a cor #3A2A1A nas barras
    private void estilizarGrafico(BarChart<String, Number> chart) {
        chart.setStyle("-fx-background-color: transparent;");
        chart.lookup(".chart-plot-background")
                .setStyle("-fx-background-color: transparent;");
        for (XYChart.Series<String, Number> s : chart.getData()) {
            for (XYChart.Data<String, Number> d : s.getData()) {
                if (d.getNode() != null) {
                    d.getNode().setStyle(
                            "-fx-bar-fill: #3A2A1A;"
                    );
                }
            }
        }
    }

    // Atualiza a largura da barra de progresso com base em atual/meta
    private void atualizarProgresso(Rectangle barra, Label txtLabelMeta, String unity, double atual, double meta) {
        double pct = Math.min(atual / meta, 1.0);
        barra.setWidth(PROGRESS_MAX_WIDTH * pct);
        txtLabelMeta.setText(String.format("%.2f / %.2f %s", atual, meta, unity));
    }

    // =============================================
    // CARROSSEL
    // =============================================
    private void mostrarSlide(int index) {
        currentIndex = index;
        slides.forEach(p -> p.setVisible(false));
        slides.get(currentIndex).setVisible(true);
    }

    private static float transformarParaFloat(Object fieldValue) {
        try {
            return Float.parseFloat(fieldValue.toString());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @FXML
    private void avancar() {
        mostrarSlide((currentIndex + 1) % slides.size());
    }

    @FXML
    private void voltar() {
        mostrarSlide((currentIndex + slides.size() - 1) % slides.size());
    }
}
