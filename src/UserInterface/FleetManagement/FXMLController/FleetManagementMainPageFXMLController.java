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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class FleetManagementMainPageFXMLController implements Initializable {
    @FXML
    private StackPane VehicleManagementMainStackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void VehicleButtonAction(ActionEvent event) throws IOException {
      
        
        VehicleNavigator.setMainController(this);
        VehicleNavigator.loadVista(VehicleNavigator.VEHICLEMAINPAGE);

    }
    
    public void VehicleClassButtonAction(ActionEvent event) throws IOException
    {
        VehicleClassNavigator.setMainController(this);
        VehicleClassNavigator.loadVista(VehicleClassNavigator.VEHICLECLASSMAINPAGE);
    }

    void ClearStackPane() {

    }

    void setStackPane(Node node) {
        VehicleManagementMainStackPane.getChildren().setAll(node);
    }
    
}
