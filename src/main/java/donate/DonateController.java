package donate;

import components.Components;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.awt.*;
import java.net.URL;
import java.util.List;
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

        // --- SLIDE 1: Bebidas ---
        XYChart.Series<String, Number> serie1 = new XYChart.Series<>();
        serie1.setName("Bebidas");
        // TODO: substituir pelos dados reais do banco
        serie1.getData().add(new XYChart.Data<>("Água", 70));
        serie1.getData().add(new XYChart.Data<>("Café", 40));
        serie1.getData().add(new XYChart.Data<>("Chá", 58));
        chart1.getData().add(serie1);
        chart1.setLegendVisible(false);
        estilizarGrafico(chart1);
        atualizarProgresso(progress1, 168, 250);

        // --- SLIDE 2: Alimentos ---
        XYChart.Series<String, Number> serie2 = new XYChart.Series<>();
        serie2.setName("Alimentos");
        // TODO: substituir pelos dados reais do banco
        serie2.getData().add(new XYChart.Data<>("Arroz", 70));
        serie2.getData().add(new XYChart.Data<>("Macarrão", 40));
        serie2.getData().add(new XYChart.Data<>("Feijão", 58));
        chart2.getData().add(serie2);
        chart2.setLegendVisible(false);
        estilizarGrafico(chart2);
        atualizarProgresso(progress2, 168, 300);

        // --- SLIDE 3: Dinheiro ---
        XYChart.Series<String, Number> serie3 = new XYChart.Series<>();
        serie3.setName("Dinheiro");
        // TODO: substituir pelos dados reais do banco
        serie3.getData().add(new XYChart.Data<>("Total", 500));
        serie3.getData().add(new XYChart.Data<>("Este mês", 320));
        serie3.getData().add(new XYChart.Data<>("Esta semana", 195));
        chart3.getData().add(serie3);
        chart3.setLegendVisible(false);
        estilizarGrafico(chart3);
        atualizarProgresso(progress3, 500, 1000);
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
    private void atualizarProgresso(Rectangle barra, double atual, double meta) {
        double pct = Math.min(atual / meta, 1.0);
        barra.setWidth(PROGRESS_MAX_WIDTH * pct);
    }

    // =============================================
    // CARROSSEL
    // =============================================
    private void mostrarSlide(int index) {
        currentIndex = index;
        slides.forEach(p -> p.setVisible(false));
        slides.get(currentIndex).setVisible(true);
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
