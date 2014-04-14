/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.FleetManagement.FXMLController;

import ControlObjects.VehicleCtrl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ModifyRatesFXMLController implements Initializable {

    @FXML
    private Font x1;
    @FXML
    private TextField VehicleClassTF;

    public TextField VehicleTypeTF;
    public TextField DailyRateTF;
    public TextField WeeklyRateTF;
    public TextField HourlyRateTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HourlyRateTF.setText(VehicleClassNavigator.vehicleClass.getHourlyPrice());
        WeeklyRateTF.setText(VehicleClassNavigator.vehicleClass.getWeeklyPrice());
        DailyRateTF.setText(VehicleClassNavigator.vehicleClass.getDailyPrice());
        VehicleClassTF.setText(VehicleClassNavigator.vehicleClass.getClassName());

        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        
    }

    @FXML
    private void ConfirmButtonAction(ActionEvent event) {
    }

    @FXML
    private void BackToSearchButtonAction(ActionEvent event) {
    }

    @FXML
    private void WeeklyRateTF(ActionEvent event) {
    }

    @FXML
    private void DailyRateTF(ActionEvent event) {
    }

    @FXML
    private void HourlyRateTF(ActionEvent event) {
    }

}
