/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReserveAdditionalEquipmentFXMLController implements Initializable {
    @FXML
    private TextField VehicleClassSelectedTF;
    public ListView AdditionalEquipmentListView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      //  VehicleClassSelectedTF.setText(ReservationNavigator.SampleSharedVariable);
        VehicleClassSelectedTF.setText(ReservationNavigator.newReserve.getVehicleClass());
        AdditionalEquipmentListView.setDisable(true);
         
    }    
    
    public void NextButtonAction(ActionEvent event) throws IOException
    {
                Object[] a = AdditionalEquipmentListView.getSelectionModel().getSelectedItems().toArray();
                ArrayList<String> SelectedEquipments = new ArrayList<>();
                for(int i=0;i < a.length;i++)
                {
                    SelectedEquipments.add(a[i].toString());
                    System.out.println(SelectedEquipments.get(i) + "   Value from ArrayList");
                }
                
                ReservationNavigator.clearVista();
                ReservationNavigator.loadVista(ReservationNavigator.ReservationCustomer);

    }
    
    public void BackButtonAction(ActionEvent event) throws IOException
    {
              ReservationNavigator.clearVista();
              ReservationNavigator.loadVista(ReservationNavigator.VEHICLECLASSAVAILABILITY);
    }
    
    public void populateListView()
    {
        ObservableList<String> items =FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App");
        AdditionalEquipmentListView.setItems(items);
    }
    
    public void RequiredCBAction(ActionEvent event)
    {
        AdditionalEquipmentListView.setDisable(false);
        AdditionalEquipmentListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        populateListView();
    }
    
    public void NotRequiredCBAction(ActionEvent event)
    {
            AdditionalEquipmentListView.getItems().clear();
            AdditionalEquipmentListView.setDisable(true);

    }
    
}
