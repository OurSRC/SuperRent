/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class ViewReservationNavigator {
    
    public static OperationsFXMLController mainController;
    
    public static final String ViewReservationsPage = "/UserInterface/Operations/FXML/ViewReservationsFXML.fxml";
    public static final String ReservationModifyOrCancelPage = "";
    
    public static void setMainController(OperationsFXMLController mainController) {
        ViewReservationNavigator.mainController = mainController;
        
}
    

    
    public static void clearVista()
    {
        mainController.ClearStackPane();
    }
    
    public static void loadVista(String fxml) throws IOException {
        mainController.setStackPane((Node) FXMLLoader.load(ViewReservationNavigator.class.getResource(fxml)));
}


    
}
