/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;


import ControlObjects.ReserveCtrl;
import Operations.Reserve;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ViewReservationsFXMLController implements Initializable {
    @FXML
    private ComboBox<?> TimeComboBox1;
    @FXML
    private ComboBox<?> TimeComboBox;
    
    public TableView ReservationTable;
    
    public TableColumn ReservationNumber;
    public TableColumn Type;
    @FXML
    private Button DisplayReservationsButton;
    @FXML
    private Button ViewReservationButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // ViewReservationButton.setDisable(true);
    }    
    
    public void getList()
    {
        ReservationTable.getItems().clear();
        ObservableList<String> list =  FXCollections.observableArrayList();
		for (int i = 0; i < 1; i++) {
			list.add("Hello");
                        list.add("Hello123");
		}
            TimeComboBox1.getItems().clear();
            Reserve sample = new Reserve();

        
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        ArrayList<Reserve> newArray = newReserveCtrl.searchReserve(sample);
        ObservableList<Reserve> slist =  FXCollections.observableArrayList(newArray);
        ReservationTable.setItems(slist);
        System.out.println("I am here and it is working");
        ReservationNumber.setCellValueFactory(new PropertyValueFactory("reservationNumber"));
        Type.setCellValueFactory( new PropertyValueFactory("type"));    
    }
    
    @FXML
    public void DisplayReservationsButtonAction(ActionEvent event)
    {
        ReservationTable.getItems().clear();
        getList();

    }

    @FXML
    private void ViewReservationButtonAction(ActionEvent event) throws IOException {

        if(!ReservationTable.getSelectionModel().isEmpty())
        {
        Reserve rrr = (Reserve) ReservationTable.getSelectionModel().getSelectedItem();
        //RentNavigator.ReservationNumber = rrr.getReservationNumber();
        //System.out.println(rrr.getReservationNumber());
        RentNavigator.loadVista(RentNavigator.ReservationSummaryPage);
        }
        
    }
    
     
    
}
