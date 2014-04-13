/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Login.FXMLController;

import UserInterface.Operations.FXMLController.OperationsFXMLController;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import entity.ReservationInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class FXMLCustomerMainPageController implements Initializable {

    @FXML
    private StackPane CustomerMainStackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ReserveButtonAction(ActionEvent event) throws IOException {
        ReservationNavigator.newReserve = new ReservationInfo();
        ReservationNavigator.setMainController(this);
        ReservationNavigator.loadVista(ReservationNavigator.VEHICLECLASSAVAILABILITY);

    }

    @FXML
    private void CustomerInfoButton(ActionEvent event) throws IOException {
        CustomerMainStackPane.getChildren().clear();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/PeopleManagement/FXML/CustomerMainPageFXML.fxml"));
        Pane CustomerInfoPane = (Pane) myLoader.load();
        CustomerMainStackPane.getChildren().add(CustomerInfoPane);
    }

    public void setStackPane(Node node) {
        CustomerMainStackPane.getChildren().setAll(node);
    }

    public void ClearStackPane() {
        CustomerMainStackPane.getChildren().clear();
    }

}
