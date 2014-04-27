/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;


import ControlObjects.PaymentCtrl;
import ControlObjects.Reservation;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import entity.Customer;
import entity.Rent;
import entity.Return;
import entity.Vehicle;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class ReturnNavigator {
        public static OperationsFXMLController mainController;

        public static final String ReturnMainPage = "/UserInterface/Operations/FXMLController/ReturnMainPageFXML.fxml";
        public static final String ReturnInformationPage= "/UserInterface/Operations/FXML/ReturnInformationPageFXML.fxml";
        public static final String RentPaymentPage = "/UserInterface/Operations/FXML/PaymentPageFXML.fxml";
        
        /* Common Variables to facilitate navigations */
        public static Rent returnRent;
        public static Reservation returnReservation;
        public static Customer returnCustomer;
        public static Vehicle returnVehicle;
        public static Return Currentreturn;
        public static PaymentCtrl newPaymentCtrl;
        public static String ActualCost;
        public static String PaymentMode;
        public static Return ReturnInfo;
       


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
