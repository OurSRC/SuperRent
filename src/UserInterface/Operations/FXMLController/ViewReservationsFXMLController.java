/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.BranchCtrl;
import ControlObjects.Reservation;
import ControlObjects.ReserveCtrl;
import SystemOperations.DateClass;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
    @FXML
    private TableColumn<?, ?> PickUpDate;

    @FXML
    private DatePicker ReserveFromTime;
    @FXML
    private DatePicker ReserveToTime;
    @FXML
    private TextField ReservationNo;
    @FXML
    private TableColumn<?, ?> VehicleClass;
    @FXML
    private TableColumn<?, ?> CustomerPhone;
    @FXML
    private TableColumn<?, ?> ReturnDate;
    @FXML
    private TableColumn<?, ?> EstimatedCost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ViewReservationButton.setDisable(true);
    }

    public void getList() throws ParseException {

        Reservation sample = new Reservation();

        LocalDate DateFrom = ReserveFromTime.getValue();
        String FromTime = TimeComboBox1.getValue().toString();
        Date reserveFromDate = DateClass.getDateTimeObject(DateFrom, FromTime);

        LocalDate ToDate = ReserveToTime.getValue();
        String ToTime = TimeComboBox.getValue().toString();
        Date reserveToDate = DateClass.getDateTimeObject(ToDate, ToTime);
        
        String reservatioNo = ReservationNo.getText();
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        ArrayList<Reservation> newArray = newReserveCtrl.searchReserveForRent(reserveFromDate,reserveToDate,BranchCtrl.getDefaultBranch(),reservatioNo);
        ObservableList<Reservation> slist = FXCollections.observableArrayList(newArray);
        ReservationTable.setItems(slist);
        System.out.println("I am here and it is working");
        ReservationNumber.setCellValueFactory(new PropertyValueFactory("reservationNo"));
        VehicleClass.setCellValueFactory(new PropertyValueFactory("vehicleClass"));
        ReturnDate.setCellValueFactory(new PropertyValueFactory("returnTime"));
        EstimatedCost.setCellValueFactory(new PropertyValueFactory("price"));
        PickUpDate.setCellValueFactory(new PropertyValueFactory("pickupTime"));
    }

    @FXML
    public void DisplayReservationsButtonAction(ActionEvent event) throws ParseException {
        ReservationTable.getItems().clear();
        getList();

    }

    @FXML
    private void ViewReservationButtonAction(ActionEvent event) throws IOException {

        /* if(!ReservationTable.getSelectionModel().isEmpty())
         {
         Reserve rrr = (Reserve) ReservationTable.getSelectionModel().getSelectedItem();
         //RentNavigator.ReservationNumber = rrr.getReservationNumber();
         //System.out.println(rrr.getReservationNumber());
         RentNavigator.loadVista(RentNavigator.ReservationSummaryPage);
         } */
        RentNavigator.loadVista(RentNavigator.ReservationSummaryPage);

    }
}
