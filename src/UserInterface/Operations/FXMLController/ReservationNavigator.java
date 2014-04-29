/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import Operate.Reservation;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import UserInterface.Login.FXMLController.FXMLCustomerMainPageController;
import Operate.ReservationInfo;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

/**
 *
 * @author Vyas
 */
public class ReservationNavigator {
        //private static OperationsFXMLController mainController;
        private static Object mainController;

        public static String SampleSharedVariable;
        public static final String VEHICLECLASSAVAILABILITY = "/UserInterface/FleetManagement/FXML/VehicleClassAvailabilityFXML.fxml";
        public static final String PickDate = "/UserInterface/Operations/FXML/ReservationPickUpdateFXML.fxml";
        public static final String ADDITIONALEQUIPMENTS = "/UserInterface/Operations/FXML/ReserveAdditionalEquipmentFXML.fxml";
        //public static final String ReservationCustomer = "/UserInterface/Operations/FXML/ReservationCustomerFXML.fxml";
        public static final String ReservationCustomer = "/UserInterface/PeopleManagement/FXML/CustomerPageForClerkReservation.fxml";
        public static final String ReservationGuestPage = "/UserInterface/PeopleManagement/FXML/CustomerPageForGuestFXML.fxml";
        public static final String ReservationSummary = "/UserInterface/Operations/FXML/ReservationSummaryFXML.fxml";
        public static final String ReserveSearchPage = "/UserInterface/Operations/FXML/ReserveSearchPageFXML.fxml";
        public static final String SelectSpecificVehicle = "/UserInterface/Operations/FXML/SelectParticularVehicleFXML.fxml";
        public static final String ViewReseration = "/UserInterface/Operations/FXML/ViewReservationsFXML.fxml";
        
        /* Common Variables to facilitate navigations */
        public static Reservation newReserve;
        public static boolean customerFlag;
        public static boolean modifyFlag;
        public static boolean reservation;

        public static void setMainController(Object mainController) {
            
        ReservationNavigator.mainController = mainController;
        
}
        
        

    public static void clearVista() throws NoSuchMethodException
    {
        try
        {
        Class aClass = mainController.getClass();
        Method method = aClass.getMethod("ClearStackPane", null);
        }catch(NoSuchMethodException e)
        {
            
        }
        //mainController.ClearStackPane();
    }
    
    public static void loadVista(String fxml) throws IOException {
        System.out.println("I am calling from here");
        try
        {Class aClass = mainController.getClass();
        Method method = aClass.getMethod("setStackPane", new Class[]{Node.class});
        method.invoke(mainController, new Object[] {(Node) FXMLLoader.load(ReservationNavigator.class.getResource(fxml))});
        }catch(NoSuchMethodException e)
        {
            
        }catch(IllegalAccessException e)
        {
            
        }catch(IllegalArgumentException e)
        {
            
        }catch(InvocationTargetException e)
        {
            
        }
        //mainController.setStackPane((Node) FXMLLoader.load(ReservationNavigator.class.getResource(fxml)));
    }
    
   
    
    public void setCustomerFlag()
    {
        customerFlag = true;
    }
}
