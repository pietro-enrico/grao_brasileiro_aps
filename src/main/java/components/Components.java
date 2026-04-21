package components;

import about.About;
import app.App;
import collaborators.Collaborators;
import donate.Donate;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import login.Login;
import my_donates.MyDonates;
import payment.Payment;
import register.Register;

import java.io.IOException;

public class Components {
    @FXML
    public  void redirectToDonate(MouseEvent event) throws IOException {
        Donate donate = new Donate();
        Stage stage = new Stage();
        donate.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public  void redirectToAbout(MouseEvent event) throws IOException {
        About about= new About();
        Stage stage = new Stage();
        about.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public  void redirectToCollaborators(MouseEvent event) throws IOException {
        Collaborators collaborators= new Collaborators();
        Stage stage = new Stage();
        collaborators.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public  void redirectToMyDonates(MouseEvent event) throws IOException {
        MyDonates myDonates= new MyDonates();
        Stage stage = new Stage();
        myDonates.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public  void redirectToPayment(MouseEvent event) throws IOException {
        Payment payment= new Payment();
        Stage stage = new Stage();
        payment.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public void clickToRegister(MouseEvent event) throws IOException {
        Register register = new Register();
        Stage stage = new Stage();
        register.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public void clickToLogin(MouseEvent event) throws IOException {
        Login login = new Login();
        Stage stage = new Stage();
        login.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public static void redirectToAppByLogin(Button button_login) throws IOException {
        App app = new App();
        Stage stage = new Stage();
        app.start(stage);

        Stage stageAtual = (Stage) button_login.getScene().getWindow();
        stageAtual.close();
    }

    @FXML
    public static void redirectToLoginByRegister(Button button_register) throws IOException {
        Login login = new Login();
        Stage stage = new Stage();
        login.start(stage);

        Stage stageAtual = (Stage) button_register.getScene().getWindow();
        stageAtual.close();
    }

    public void redirectToApp(MouseEvent event) throws IOException {
        App app = new App();
        Stage stage = new Stage();
        app.start(stage);

        Stage stageAtual = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stageAtual.close();
    }
}
