/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Login.FXMLController;

import Operate.Reservation;
import UserInterface.MainController;
import UserInterface.Operations.FXMLController.OperationsFXMLController;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import Operate.ReservationInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.StackPane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.LabelBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class FXMLCustomerMainPageController implements Initializable {

    @FXML
    private StackPane CustomerMainStackPane;

    public Button LogoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ReservationNavigator.newReserve = new Reservation();
            ReservationNavigator.reservation = true;
            ReservationNavigator.setMainController(this);
            ReservationNavigator.loadVista(ReservationNavigator.VEHICLECLASSAVAILABILITY);
        } catch (IOException ex) {
            Logger.getLogger(FXMLCustomerMainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void ReserveButtonAction(ActionEvent event) throws IOException {
        ReservationNavigator.newReserve = new Reservation();
        ReservationNavigator.reservation = true;
        ReservationNavigator.setMainController(this);
        ReservationNavigator.loadVista(ReservationNavigator.VEHICLECLASSAVAILABILITY);

    }

    @FXML
    private void CustomerInfoButton(ActionEvent event) throws IOException {
        CustomerMainStackPane.getChildren().clear();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/PeopleManagement/FXML/CustomerPageForCustomer.fxml"));
        Pane CustomerInfoPane = (Pane) myLoader.load();
        CustomerMainStackPane.getChildren().add(CustomerInfoPane);
    }

    public void setStackPane(Node node) {
        CustomerMainStackPane.getChildren().setAll(node);

    }

    public void ClearStackPane() {
        CustomerMainStackPane.getChildren().clear();
    }

    public void LogoutAction(ActionEvent event) throws IOException {
        System.out.println("Inside the logout action");
     
        System.out.println("Inside the logout action");
        final Stage dialog = new Stage();
        final Stage stage = (Stage) LogoutButton.getScene().getWindow();
        stage.close();
        stage.close();
        Stage MainStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(LoginNavigator.MAIN));
        Scene scene = new Scene(mainPane);
        MainStage.setScene(scene);
        MainController mainController = loader.getController();
        mainController.SetStage(MainStage);
        LoginNavigator.setMainController(mainController);
        LoginNavigator.loadVista(LoginNavigator.VISTA_1);
        MainStage.show();
        MainStage.setResizable(false);
        MainStage.setWidth(630);
        MainStage.setHeight(530);
        MainStage.show();
    }

}
