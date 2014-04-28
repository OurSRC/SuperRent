/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Login.FXMLController;

import ControlObjects.Reservation;
import UserInterface.MainController;
import UserInterface.Operations.FXMLController.OperationsFXMLController;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import entity.ReservationInfo;
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
        // TODO
    }

    @FXML
    private void ReserveButtonAction(ActionEvent event) throws IOException {
        ReservationNavigator.newReserve = new Reservation();
        ReservationNavigator.reservation=true;
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
    
    public void LogoutAction(ActionEvent event)
    {
        System.out.println("Inside the logout action");
        final Stage dialog = new Stage();
        final Stage stage = (Stage) LogoutButton.getScene().getWindow();

        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);

        dialog.setScene(new Scene(HBoxBuilder.create().styleClass("modal-dialog").children(
                LabelBuilder.create().text("Would you like to logout?").build(),
                ButtonBuilder.create().text("Yes").defaultButton(true).onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            // take action and close the dialog.
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
                            MainStage.setHeight(509);
                            MainStage.show();
                            stage.getScene().getRoot().setEffect(null);
                            dialog.close();
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLClerkMainPageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).build(),
                ButtonBuilder.create().text("No").cancelButton(true).onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        stage.setOpacity(1);

                        stage.getScene().getRoot().setEffect(null);
                        dialog.close();
                    }
                }).build()
        ).build(), null
        )
        );
        stage.setOpacity(.75);
        dialog.show();
    }

}
