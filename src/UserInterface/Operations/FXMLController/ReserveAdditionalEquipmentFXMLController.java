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
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReserveAdditionalEquipmentFXMLController implements Initializable {
    @FXML
    private TextField VehicleClassSelectedTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      //  VehicleClassSelectedTF.setText(ReservationNavigator.SampleSharedVariable);
        VehicleClassSelectedTF.setText(ReservationNavigator.newReserve.type);
    }    
    
    public void NextButtonAction(ActionEvent event) throws IOException
    {
                ReservationNavigator.clearVista();

                ReservationNavigator.loadVista(ReservationNavigator.ReservationCustomer);

    }
    
    public void BackButtonAction(ActionEvent event) throws IOException
    {
              ReservationNavigator.clearVista();
              ReservationNavigator.loadVista(ReservationNavigator.PickDate);
    }
    
}
