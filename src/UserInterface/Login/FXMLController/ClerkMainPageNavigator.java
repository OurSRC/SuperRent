/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Login.FXMLController;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class ClerkMainPageNavigator {
    
        private static FXMLClerkMainPageController mainController;

        public static final String HOME_PAGE = "/UserInterface/Login/FXML/ClerkHomePageFXML.fxml";
        public static final String OPERATIONS = "/UserInterface/Operations/FXML/OperationsFXML.fxml";
        public static final String REPORTS = "/UserInterface/Reports/FXML/ReportsMainPageFXML.fxml";
        public static final String FLEET_MANAGEMENT = "/UserInterface/FleetManagement/FXML/FleetManagementMainPageFXML.fxml";
        public static final String USER_MANAGEMENT = "/UserInterface/PeopleManagement/FXML/PeopleManagementFXML.fxml";
        public static String CurrentUserName ;
        public static boolean staff;

    
    public static void setMainController(FXMLClerkMainPageController mainControlle) {
    ClerkMainPageNavigator.mainController = mainControlle;
}
    
    
    public static void loadVista(String fxml) {
    try {
    mainController.setStackPane((Node) FXMLLoader.load(ClerkMainPageNavigator.class.getResource(fxml)));
    } catch (IOException e) {
        e.printStackTrace();
       }
}

    
}
