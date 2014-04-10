/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.FleetManagement.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class AddVehicleClassFXMLController implements Initializable {
    @FXML
    private TextField VehicleClassTF;
    @FXML
    private TextField HourlyRateTF;
    @FXML
    private TextField DailyRateTF;
    @FXML
    private TextField WeeklyRateTF;
    @FXML
    private ToggleGroup VehicleTypeTG;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void VehicleTypeCarAction(ActionEvent event) {
    }

    @FXML
    private void VehicleTypeTruckAction(ActionEvent event) {
    }

    @FXML
    private void AddVehicleClassButtonAction(ActionEvent event) {
    }

    @FXML
    private void BackToSearchButtonAction(ActionEvent event) throws IOException {
        VehicleClassNavigator.loadVista(VehicleClassNavigator.VEHICLECLASSMAINPAGE);
    }
    
}
