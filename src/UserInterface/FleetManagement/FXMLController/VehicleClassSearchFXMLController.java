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
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class VehicleClassSearchFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void ModifyVehicleClassButtonAction(ActionEvent event) throws IOException
    {
        System.out.println("I am calling heere");
        
                VehicleClassNavigator.VehicleClass = " Sample ";

        VehicleClassNavigator.loadVista(VehicleClassNavigator.MODIFYVEHICLECLASSPAGE);
    }
    
    public void AddVehicleClassButtonAction(ActionEvent event) throws IOException
    {
                VehicleClassNavigator.loadVista(VehicleClassNavigator.ADDVEHICLECLASSPAGE);
    }
    
}
