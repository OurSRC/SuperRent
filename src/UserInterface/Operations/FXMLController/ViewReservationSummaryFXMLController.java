/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ViewReservationSummaryFXMLController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private TextField PickUpDateTF;
    @FXML
    private TextField ReturnDateTF;
    @FXML
    private TextField VehicleClassTF;
    @FXML
    private TextField VehicleTypeTF;
    @FXML
    private TextField AdditionalEquipmentsTF;
    @FXML
    private TextField RentChargesTF;
    @FXML
    private TextField AdditionalChargesTF;
    @FXML
    private TextField InsuranceChargesTF;
    @FXML
    private TextField TotalTF;
    
    @FXML
    private Label ReservationNumberLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReservationNumberLabel.setText(RentNavigator.ReservationNumber);
    }    

    @FXML
    private void ModifyReservationButtonAction(ActionEvent event) {
    }

    @FXML
    private void CancelReservationButtonAction(ActionEvent event) {
    }
    
    @FXML
    private void BackButtonAction(ActionEvent event) throws IOException
    {
        RentNavigator.loadVista(RentNavigator.ReserveSearchPage);
    }
    
    public void ProceedToRentActionButton(ActionEvent event) throws IOException
    {
        RentNavigator.loadVista(RentNavigator.SelectSpecificVehicle);
    }
    
    
    
}
