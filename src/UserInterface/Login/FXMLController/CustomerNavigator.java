/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Login.FXMLController;

import UserInterface.Operations.FXMLController.OperationsFXMLController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class CustomerNavigator {

    private static OperationsFXMLController mainController;

    public static String CurrentUserName;
    public static final String OPERATIONS = "/UserInterface/Operations/FXML/OperationsFXML.fxml";

    public static void setMainController(OperationsFXMLController mainControlle) {
        CustomerNavigator.mainController = mainControlle;
    }

    public static void loadVista(String fxml) {
        try {
            mainController.setStackPane((Node) FXMLLoader.load(CustomerNavigator.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
