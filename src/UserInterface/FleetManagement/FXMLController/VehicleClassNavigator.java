/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.FleetManagement.FXMLController;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class VehicleClassNavigator {
    
        public static FleetManagementMainPageFXMLController mainController;
        public static final String VEHICLECLASSMAINPAGE = "/UserInterface/FleetManagement/FXML/VehicleClassSearchFXML.fxml";
        public static final String MODIFYVEHICLECLASSPAGE = "/UserInterface/FleetManagement/FXML/ModifyRatesFXML.fxml";
        public static final String ADDVEHICLECLASSPAGE = "/UserInterface/FleetManagement/FXML/AddVehicleClassFXML.fxml";

        /* Variable to store the screen attributes */
        public static String VehicleClass;
        
        public static void setMainController(FleetManagementMainPageFXMLController mainController) {
        VehicleClassNavigator.mainController = mainController;
        
}
   
    public static void clearVista()
    {
        mainController.ClearStackPane();
    }
    
    public static void loadVista(String fxml) throws IOException {
        mainController.setStackPane((Node) FXMLLoader.load(VehicleClassNavigator.class.getResource(fxml)));
}
    
}
