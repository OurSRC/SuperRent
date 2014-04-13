
package UserInterface.Operations.FXMLController;

import Operations.Reserve;
import entity.ReservationInfo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
       ReservationNavigator.newReserve = new ReservationInfo();
       ReservationNavigator.setMainController(this);
       ReservationNavigator.loadVista(ReservationNavigator.VEHICLECLASSAVAILABILITY);
    }
    
   
    @FXML
    public void ReturnVehicleAction(ActionEvent event) throws IOException
    {
        ReturnNavigator.setMainController(this);    
        ReturnNavigator.loadVista(ReturnNavigator.ReturnMainPage);

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
       RentNavigator.loadVista(RentNavigator.ReserveSearchPage);

    }
    
    @FXML
    public void ViewRentAction(ActionEvent event) throws IOException
    {
       /* MainReservationStackPane.getChildren().clear();*/
        
    }
    
    @FXML
    public void ViewReservationAction(ActionEvent event) throws IOException
    {
       ViewReservationNavigator.setMainController(this);    
       ViewReservationNavigator.loadVista(ViewReservationNavigator.ViewReservationsPage);
    }
    
    
    public void CustomerButtonAction(ActionEvent event) throws IOException
    {
        MainOperationsStackPane.getChildren().clear();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/PeopleManagement/FXML/CustomerMainPageFXML.fxml"));
        Pane CustomerInfoPane = (Pane) myLoader.load();
        MainOperationsStackPane.getChildren().add(CustomerInfoPane);
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
        FadeTransitionMethod(node);
    }
    
    public void ClearStackPane()
    {
        MainOperationsStackPane.getChildren().clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
