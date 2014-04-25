/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ViewReservationSummaryFXMLController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private TextField PickUpDateTF;
    @FXML
    private TextField ReturnDateTF;
    @FXML
    private TextField VehicleClassTF;
    @FXML
    private TextField VehicleTypeTF;
    @FXML
    private TextField AdditionalEquipmentsTF;
    @FXML
    private TextField EstimatedCostTF;
    @FXML
    private ListView AdditionalEquipmentsList;
    @FXML
    private ListView InsuranceList;
    @FXML
    private TextField TotalTF;
    
    @FXML
    private Label ReservationNumberLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReservationNumberLabel.setText(RentNavigator.ReservationNumber);
        PickUpDateTF.setText(RentNavigator.selectedReservation.getPickupTime().toString());
        ReturnDateTF.setText(RentNavigator.selectedReservation.getReturnTime().toString());
        VehicleClassTF.setText(RentNavigator.selectedReservation.getVehicleClass());
        
        if (RentNavigator.selectedReservation.getEquipmentType().size() != 0) {
            System.out.println("Inside Additional Equipments");
            ObservableList<String> items = FXCollections.observableArrayList(RentNavigator.selectedReservation.getEquipmentType());
            AdditionalEquipmentsList.setItems(items);
        }
        
        if (!RentNavigator.selectedReservation.getInsurance().isEmpty()) {
            ObservableList<String> items = FXCollections.observableArrayList(RentNavigator.selectedReservation.getInsurance());
            InsuranceList.setItems(items);
        }
    }    
    
    @FXML
    private void BackButtonAction(ActionEvent event) throws IOException
    {
        RentNavigator.loadVista(RentNavigator.ReserveSearchPage);
    }
    
    @FXML
    public void ProceedToRentActionButton(ActionEvent event) throws IOException
    {
        RentNavigator.loadVista(RentNavigator.SelectSpecificVehicle);
    }
    
    
    
}
