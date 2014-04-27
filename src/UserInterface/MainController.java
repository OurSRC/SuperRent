package UserInterface;

import ControlObjects.Reservation;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import UserInterface.Login.FXMLController.CustomerNavigator;
import UserInterface.Login.FXMLController.LoginNavigator;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main controller class for the entire layout.
 */
public class MainController implements Initializable{

    /**
     * Holder of a switchable vista.
     */
    @FXML
    private StackPane LoginStackPane;
    public Button CloseButton;
    public Stage PrevStage;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *     
* @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        LoginStackPane.getChildren().setAll(node);
    }

    public void LoginButtonAction(ActionEvent event) {
        LoginNavigator.loadVista(LoginNavigator.VISTA_1);
    }

    public void RegisterButtonAction(ActionEvent event) {
        LoginNavigator.loadVista(LoginNavigator.Register);
    }

    public void SetStage(Stage stage) throws IOException {
        this.PrevStage = stage;
    }

    @FXML
    private void CloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
    
    public void GuestReserveButtonAction(ActionEvent event) throws IOException
    {
        ReservationNavigator.newReserve = new Reservation();
        ReservationNavigator.reservation=true;
        ReservationNavigator.setMainController(this);
        ReservationNavigator.loadVista(ReservationNavigator.VEHICLECLASSAVAILABILITY);
    }
    
     public void setStackPane(Node node) {
        LoginStackPane.getChildren().setAll(node);
    }

    public void ClearStackPane() {
        LoginStackPane.getChildren().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClerkMainPageNavigator.staff=false;
        CustomerNavigator.customerFlag=false;
    }

}
