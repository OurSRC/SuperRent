/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import Operations.Reserve;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReserveSearchPageFXMLController implements Initializable {
    @FXML
    private Label PickUpLabel;
    @FXML
    private Font x1;
    @FXML
    private Label ReturnDateLabel;
    @FXML
    private TextField PickUpDateLabel;
    @FXML
    private TextField ReturnDateTF;
    @FXML
    private Label VehicleClassLabel;
    @FXML
    private Label VehicleTypeLabel;
    @FXML
    private TextField VehicleClassTF;
    @FXML
    private TextField VehicleTypeTF;
    @FXML
    private Label AdditionalEquipLabel;
    @FXML
    private TextField AdditionalEquipTF;
    
    public Pane SummaryPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        SummaryPane.setVisible(false);
        
    }    

    
    
    @FXML
    public void ProceedToRentButtonAction(ActionEvent event)
    {
        try {
            RentNavigator.clearVista();
            RentNavigator.loadVista(RentNavigator.SelectSpecificVehicle);
        } catch (IOException ex) {
            Logger.getLogger(ReserveSearchPageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void FadeTransitionMethod(Node CurrentNode)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), CurrentNode);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    
    @FXML
    public void SearchReservationActionButton(ActionEvent event)
    {
        SummaryPane.setVisible(true);
    }

    @FXML
    private void NewReservationButtonAction(ActionEvent event) throws IOException {  
      //ReservationNavigator.loadVista(ReservationNavigator.PickDate);
        ReservationNavigator.newReserve = new Reserve();
        ReservationNavigator.setMainController(RentNavigator.mainController);
        ReservationNavigator.loadVista(ReservationNavigator.PickDate);

    }
    
}
