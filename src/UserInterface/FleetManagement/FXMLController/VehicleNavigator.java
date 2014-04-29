/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.FleetManagement.FXMLController;

import Vehicle.Vehicle;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class VehicleNavigator {
    
        public static FleetManagementMainPageFXMLController mainController;
        public static final String VEHICLEMAINPAGE = "/UserInterface/FleetManagement/FXML/VehicleSearchFXML.fxml";
        public static final String ADDVEHICLE = "/UserInterface/FleetManagement/FXML/AddVehicleFXML.fxml";
        public static final String MODIFYVEHICLE = "/UserInterface/FleetManagement/FXML/ModifyVehiclePageFXML.fxml";

        /* Variables to Store the parameters */
        public static Vehicle Modifyvehicle;
        
        public static void setMainController(FleetManagementMainPageFXMLController mainController) {
        VehicleNavigator.mainController = mainController;
        
}
   
    public static void clearVista()
    {
        mainController.ClearStackPane();
    }
    
    public static void loadVista(String fxml) throws IOException {
        mainController.setStackPane((Node) FXMLLoader.load(VehicleNavigator.class.getResource(fxml)));
}
    
}
