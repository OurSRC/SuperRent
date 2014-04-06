/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class SelectParticularVehicleFXMLController implements Initializable {
    @FXML
    private Button BackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(ReservationNavigator.rentFlag)
        {
            BackButton.setDisable(true);
        }
    }    

    @FXML
    private void BackButtonAction(ActionEvent event) throws IOException {
        ReservationNavigator.clearVista();
        ReservationNavigator.loadVista(ReservationNavigator.ReserveSearchPage);
    }

    @FXML
    private void VehicleTypeSelectedTF(ActionEvent event) {
    }

    @FXML
    private void NextButtonAction(ActionEvent event) {
    }
    
}
