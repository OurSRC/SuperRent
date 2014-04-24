/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;


import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import entity.Rent;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class ReturnNavigator {
        public static OperationsFXMLController mainController;

        public static final String ReturnMainPage = "/UserInterface/Operations/FXML/ReturnMainPageFXML.fxml";
        public static final String ReturnInformationPage= "/UserInterface/Operations/FXML/ReturnInformationPageFXML.fxml";
        public static final String RentConfirmPage = "";
        
        /* Common Variables to facilitate navigations */
        public static Rent returnRent;


        public static void setMainController(OperationsFXMLController mainController) {
        ReturnNavigator.mainController = mainController;
        
}
    

    
    public static void clearVista()
    {
        mainController.ClearStackPane();
    }
    
    public static void loadVista(String fxml) throws IOException {
        mainController.setStackPane((Node) FXMLLoader.load(ClerkMainPageNavigator.class.getResource(fxml)));
}

}
