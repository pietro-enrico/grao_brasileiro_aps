package payment;

import components.Components;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class PaymentController extends Components {

    @FXML
    private ComboBox<String> cbDesejaDoar;
    @FXML
    private ComboBox<String> cbSuprimento;
    @FXML
    private TextField tfQuantidade;
    @FXML
    private TextField tfValor;

    @FXML
    private Label lblSuprimento;
    @FXML
    private Label lblQuantidade;
    @FXML
    private Label lblValor;

    @FXML
    private ToggleButton btnPix;
    @FXML
    private ToggleButton btnCC;
    @FXML
    private ToggleButton btnPayPal;
    @FXML
    private ToggleButton btnCorreios;

    @FXML
    private Button btnConfirmar;

    @FXML
    private Pane qrCodeBox;
    @FXML
    private Label lblValorDoado;
    @FXML
    private Label lblCodigoPix;
    @FXML
    private ImageView qrImageView;

    @FXML
    private Pane correiosBox;
    @FXML
    private Label lblCodigoCorreios;
    @FXML
    private Button btnBaixarSelo;

    private final ToggleGroup pagamentoGroup = new ToggleGroup();

    private String codigoCorreiosAtual = "";
    private String codigoPixAtual = "";

    private final ObservableList<String> listaAlimentos = FXCollections.observableArrayList(
            "Selecione o alimento", "Arroz", "Feijão", "Macarrão", "Óleo", "Açúcar", "Farinha"
    );
    private final ObservableList<String> listaBebidas = FXCollections.observableArrayList(
            "Selecione a bebida", "Leite", "Suco", "Água mineral", "Achocolatado", "Café"
    );

    @FXML
    public void initialize() {
        // Associa os botões ao ToggleGroup
        btnPix.setToggleGroup(pagamentoGroup);
        btnCC.setToggleGroup(pagamentoGroup);
        btnPayPal.setToggleGroup(pagamentoGroup);
        btnCorreios.setToggleGroup(pagamentoGroup);

        // Dropdown principal
        cbDesejaDoar.getItems().addAll("Dinheiro", "Alimento", "Bebida");
        cbDesejaDoar.setPromptText("Selecione o alimento");
        cbDesejaDoar.setValue(null);
        cbDesejaDoar.valueProperty().addListener((obs, oldV, newV) -> atualizarCampos(newV));

        // Animação (glow) nos botões
        configurarGlow(btnPix, Color.web("#1DB8A8"));
        configurarGlow(btnCC, Color.web("#333333"));
        configurarGlow(btnPayPal, Color.web("#1E88E5"));
        configurarGlow(btnCorreios, Color.web("#FFC107"));
    }

    private void configurarGlow(ToggleButton btn, Color cor) {
        btn.selectedProperty().addListener((obs, ant, novo) -> {
            if (novo) {
                DropShadow glow = new DropShadow();
                glow.setColor(cor);
                glow.setSpread(0.5);
                glow.setRadius(0);
                btn.setEffect(glow);

                new Timeline(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(glow.radiusProperty(), 0)),
                        new KeyFrame(Duration.millis(400),
                                new KeyValue(glow.radiusProperty(), 22, Interpolator.EASE_OUT))
                ).play();
            } else {
                btn.setEffect(null);
            }
        });
    }

    private void atualizarCampos(String opcao) {
        boolean isDinheiro = "Dinheiro".equals(opcao);
        boolean isAlimento = "Alimento".equals(opcao);
        boolean isBebida = "Bebida".equals(opcao);
        boolean isFisico = isAlimento || isBebida;

        if (isAlimento) {
            lblSuprimento.setText("Qual tipo de alimento?");
            cbSuprimento.setItems(listaAlimentos);
            cbSuprimento.getSelectionModel().selectFirst();
        } else if (isBebida) {
            lblSuprimento.setText("Qual tipo de bebida?");
            cbSuprimento.setItems(listaBebidas);
            cbSuprimento.getSelectionModel().selectFirst();
        }

        lblSuprimento.setVisible(isFisico);
        cbSuprimento.setVisible(isFisico);
        lblQuantidade.setVisible(isFisico);
        tfQuantidade.setVisible(isFisico);

        lblValor.setVisible(isDinheiro);
        tfValor.setVisible(isDinheiro);

        btnPix.setVisible(isDinheiro);
        btnCC.setVisible(isDinheiro);
        btnPayPal.setVisible(isDinheiro);
        btnCorreios.setVisible(isFisico);

        pagamentoGroup.selectToggle(null);
        qrCodeBox.setVisible(false);
        correiosBox.setVisible(false);
    }

    @FXML
    private void confirmarDoacao() {
        String opcao = cbDesejaDoar.getValue();

        if (opcao == null || "Selecione o que doar".equals(opcao)) {
            alerta("Selecione o que deseja doar.");
            return;
        }

        Toggle selecionado = pagamentoGroup.getSelectedToggle();
        if (selecionado == null) {
            alerta("Selecione uma forma de pagamento.");
            return;
        }

        if (selecionado == btnPix && "Dinheiro".equals(opcao)) {
            gerarPix();
        } else if (selecionado == btnCorreios && ("Alimento".equals(opcao) || "Bebida".equals(opcao))) {
            gerarSeloCorreios();
        } else if (selecionado == btnCC || selecionado == btnPayPal) {
            alerta("Doação cadastrada com sucesso via "
                    + ((ToggleButton) selecionado).getText() + "!");
            qrCodeBox.setVisible(false);
            correiosBox.setVisible(false);
        } else {
            alerta("Forma de pagamento incompatível com o tipo de doação.");
        }
    }

    private void gerarPix() {
        String valor = tfValor.getText().isEmpty() ? "0,00" : tfValor.getText();
        codigoPixAtual = "00020126580014BR.GOV.BCB.PIX0136GRAO-"
                + System.currentTimeMillis() + "5204000053039865802BR6009SAOPAULO";

        lblValorDoado.setText("VALOR DOADO:\nR$ " + valor);
        lblCodigoPix.setText("Código PIX:\n" + codigoPixAtual);

        try {
            Image img = new Image(
                    "https://api.qrserver.com/v1/create-qr-code/?size=200x200&data=" + codigoPixAtual,
                    160, 160, true, true, true);
            img.errorProperty().addListener((o, a, erro) -> {
                if (erro) System.err.println("Falha ao carregar QR: verifique internet.");
            });
            qrImageView.setImage(img);
        } catch (Exception e) {
            System.err.println("Erro no QR: " + e.getMessage());
        }

        correiosBox.setVisible(false);
        qrCodeBox.setVisible(true);
    }

    private void gerarSeloCorreios() {
        codigoCorreiosAtual = gerarCodigoRastreio();
        lblCodigoCorreios.setText(codigoCorreiosAtual);
        qrCodeBox.setVisible(false);
        correiosBox.setVisible(true);
    }

    private String gerarCodigoRastreio() {
        Random r = new Random();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < 9; i++) num.append(r.nextInt(10));
        return "BR" + num + "BR";
    }

    @FXML
    private void baixarSeloCorreios() {
        if (codigoCorreiosAtual == null || codigoCorreiosAtual.isEmpty()) {
            alerta("Nenhum selo gerado ainda.");
            return;
        }

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Salvar selo dos Correios");
        chooser.setInitialFileName("selo_" + codigoCorreiosAtual + ".txt");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Arquivo de texto (*.txt)", "*.txt"));

        File arquivo = chooser.showSaveDialog(btnBaixarSelo.getScene().getWindow());
        if (arquivo == null) return;

        try (FileWriter fw = new FileWriter(arquivo)) {
            fw.write(montarConteudoSelo());
            fw.flush();
            alerta("Selo salvo em:\n" + arquivo.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
            alerta("Erro ao salvar o arquivo:\n" + e.getMessage());
        }
    }

    private String montarConteudoSelo() {
        String tipo = cbDesejaDoar.getValue();
        String suprimento = cbSuprimento.getValue();
        String qtd = tfQuantidade.getText().isEmpty() ? "—" : tfQuantidade.getText();

        return "============================================\n"
                + "       SELO DE POSTAGEM - CORREIOS         \n"
                + "============================================\n\n"
                + "Código de rastreio: " + codigoCorreiosAtual + "\n\n"
                + "--------------------------------------------\n"
                + "DESTINATÁRIO:\n"
                + "  Grão Brasileiro - Centro de Doações\n"
                + "  Rua das Flores, 123\n"
                + "  São Paulo / SP\n"
                + "  CEP: 01234-567\n"
                + "--------------------------------------------\n\n"
                + "CONTEÚDO DA DOAÇÃO:\n"
                + "  Categoria:  " + tipo + "\n"
                + "  Item:       " + suprimento + "\n"
                + "  Quantidade: " + qtd + "\n\n"
                + "============================================\n"
                + "   Cole este selo na embalagem da encomenda \n"
                + "============================================\n";
    }

    private void alerta(String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}