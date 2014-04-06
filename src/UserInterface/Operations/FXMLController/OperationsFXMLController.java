
package UserInterface.Operations.FXMLController;

import Operations.Reserve;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.animation.FadeTransition;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Vyas
 */
public class OperationsFXMLController implements Initializable{
    
    @FXML   
    public StackPane MainOperationsStackPane;
    public TextField VehicleClassSelectedTF;
    
    
    public void CreateReservationAction(ActionEvent event) throws IOException
    {
       MainOperationsStackPane.getChildren().clear();
       CreateReservationFXMLController ReservationContoller = new CreateReservationFXMLController();
       ResetCommonAttributes();
       ReservationNavigator.newReserve = new Reserve();
       ReservationNavigator.setMainController(this);
       ReservationNavigator.loadVista(ReservationNavigator.PickDate);
    }
    
     @FXML
    public void CustomerDetailsButtonAction(ActionEvent event) throws IOException
    {
       /* MainReservationStackPane.getChildren().clear();
        Pane CustomerDetailsPane = FXMLLoader.load(getClass().getResource("/PeopleManagement/CustomerMainPageFXML.fxml"));
        MainReservationStackPane.getChildren().add(CustomerDetailsPane); 
        FadeTransitionMethod(CustomerDetailsPane); */

    }
    
    public void QueryReservationAction(ActionEvent event) throws IOException
    {
        /*MainReservationStackPane.getChildren().clear();*/
        
        
    }
    
    public void ViewVehicleAvailabilityButtonAction(ActionEvent event) throws IOException
    {
       /* MainReservationStackPane.getChildren().clear();
        Pane OperationsPane = FXMLLoader.load(getClass().getResource("/FleetManagement/VehicleAvailabilityFXML.fxml"));
        MainReservationStackPane.getChildren().add(OperationsPane);
        FadeTransitionMethod(OperationsPane);*/

        
    }

   
    @FXML
    public void ReturnVehicleAction(ActionEvent event) throws IOException
    {
       /*MainReservationStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("ReturnMainPageFXML.fxml"));
       FXMLReturnVehicleController ReturnLoader = (FXMLReturnVehicleController) myLoader.getController();
       FXMLReturnVehicleController ReturnController = new FXMLReturnVehicleController();
       Pane RentalReservation  = (Pane)myLoader.load();
       MainReservationStackPane.getChildren().add(RentalReservation);
       FadeTransitionMethod(RentalReservation);*/

    }
    
    @FXML
    public void CancelReservationAction(ActionEvent event)
    {
       /*MainReservationStackPane.getChildren().clear();*/
    }
    
    @FXML
    public void RentVehicleAction(ActionEvent event) throws IOException
    {

       RentNavigator.setMainController(this);
       RentNavigator.loadVista(ReservationNavigator.ReserveSearchPage);

    }
    
    @FXML
    public void ViewRentAction(ActionEvent event) throws IOException
    {
       /* MainReservationStackPane.getChildren().clear();*/
        
    }
    
    @FXML
    public void ViewReservationAction(ActionEvent event) throws IOException
    {
        /*MainReservationStackPane.getChildren().clear();
        Pane OperationsPane = FXMLLoader.load(getClass().getResource("/Operations/ViewReservationsFXML.fxml"));
        MainReservationStackPane.getChildren().add(OperationsPane);
        FadeTransitionMethod(OperationsPane);*/
        
       //ResetCommonAttributes();
       ViewReservationNavigator.setMainController(this);    
       ViewReservationNavigator.loadVista(ViewReservationNavigator.ViewReservationsPage);
    }
   
    public void FadeTransitionMethod(Node CurrentNode)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), CurrentNode);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    
    public void setStackPane(Node node)
    {
        MainOperationsStackPane.getChildren().setAll(node);
    }
    
    public void ClearStackPane()
    {
        MainOperationsStackPane.getChildren().clear();
    }
    
    public void ResetCommonAttributes()
    {
        ReservationNavigator.rentFlag=false;
        ReservationNavigator.newRent=null;
 ReservationNavigator.newReserve=null;
 ReservationNavigator.modifyFlag=false;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
}
