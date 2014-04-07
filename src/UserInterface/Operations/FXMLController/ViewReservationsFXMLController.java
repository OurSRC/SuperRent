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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
        // TODO   
        ViewReservationButton.setDisable(true);
    }    
    
    public void getList()
    {
        ObservableList<String> list =  FXCollections.observableArrayList();
		for (int i = 0; i < 1; i++) {
			list.add("Hello");
                        list.add("Hello123");
		}
            TimeComboBox1.getItems().clear();
            Reserve sample = new Reserve();

        
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        
        ArrayList<Reserve> newArray = newReserveCtrl.searchReserve(sample);
        
        System.out.println(newArray.get(1).reservationNumber);
        ObservableList<ReserveSample> slist =  FXCollections.observableArrayList();
        for(int i=0;i <newArray.size();i++)
        {
            slist.add(new ReserveSample(newArray.get(i).reservationNumber, newArray.get(i).type));
        }
        ReservationTable.setItems(slist);
       // Reserve person = (Reserve)ReservationTable.getSelectionModel().getSelectedItem();
        //System.out.println(person.reservationNumber);
    
        ReservationNumber.setCellValueFactory(new PropertyValueFactory<ReserveSample,String>("ReservationNumber"));
        Type.setCellValueFactory(new PropertyValueFactory<ReserveSample,String>("Type"));
        
        ReservationTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ReserveSample>() {

    @Override
    public void changed(ObservableValue<? extends ReserveSample> observable,ReserveSample oldValue, ReserveSample newValue) {
      System.out.println(newValue.getReservationNumber());
      ViewReservationButton.setDisable(false);
    }

      
  });
    
    }
    
    @FXML
    public void DisplayReservationsButtonAction(ActionEvent event)
    {
        ReservationTable.setItems(null);
        getList();
    }

    @FXML
    private void ViewReservationButtonAction(ActionEvent event) {
    }
    
     
    
}
