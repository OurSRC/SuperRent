/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;


import Operate.Reservation;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import Operate.Rent;
import Vehicle.Vehicle;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class RentNavigator {
        public static OperationsFXMLController mainController;
        public static final String ReserveSearchPage = "/UserInterface/Operations/FXML/ViewReservationsFXML.fxml";
        public static final String SelectSpecificVehicle = "/UserInterface/Operations/FXML/SelectParticularVehicleFXML.fxml";
        public static final String ReservationSummaryPage = "/UserInterface/Operations/FXML/ViewReservationForRentFXML.fxml";
        public static final String RentDetailsPage = "/UserInterface/Operations/FXML/RentDetailPageFXML.fxml";
        
        /* Common Variables to facilitate navigations */
        public static Rent newRent;
        public static String ReservationNumber;
        public static Vehicle RentVehicle;
        public static Reservation selectedReservation;


        public static void setMainController(OperationsFXMLController mainController) {
        RentNavigator.mainController = mainController;
        
}
   
    public static void clearVista()
    {
        mainController.ClearStackPane();
    }
    
    public static void loadVista(String fxml) throws IOException {
        mainController.setStackPane((Node) FXMLLoader.load(RentNavigator.class.getResource(fxml)));
}

}
