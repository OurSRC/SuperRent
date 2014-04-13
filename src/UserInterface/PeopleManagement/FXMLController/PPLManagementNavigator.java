/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.PeopleManagement.FXMLController;

import UserInterface.Operations.FXMLController.OperationsFXMLController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Ali
 */
public class PPLManagementNavigator {
    public static PeopleManagementFXMLController mainController;

    
    public static final String AddAUser = "/UserInterface/PeopleManagement/FXML/AddUserFXML.fxml";
    public static final String UserProfile = "/UserInterface/PeopleManagement/FXML/UserProfileRemoveFXML.fxml";
    public static final String UserSearch = "/UserInterface/PeopleManagement/FXML/UserSearchFXML.fxml";

    public static void setMainController(PeopleManagementFXMLController mainController) {
        PPLManagementNavigator.mainController = mainController;
    }
    
    public static void clearVista()
    {
        mainController.ClearStackPane();
    }
    
    public static void loadVista(String fxml) throws IOException {
        mainController.setStackPane((Node) FXMLLoader.load(PPLManagementNavigator.class.getResource(fxml)));
    }


}   


