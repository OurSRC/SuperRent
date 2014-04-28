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
    @FXML
    private TableColumn CustomerNameColumn;
    @FXML
    private TableColumn EstimatedCostColumn;

    /**
     * Initializes the controller class.
     */
    Date reserveFromDate;
    Date reserveToDate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // ViewReservationButton.setDisable(true);
    }

    public void getList() throws ParseException {

        Reservation sample = new Reservation();

        if (ReserveFromTime.valueProperty().isNotNull().getValue()) {
            LocalDate DateFrom = ReserveFromTime.getValue();
            String FromTime = TimeComboBox1.getValue().toString();
            reserveFromDate = DateClass.getDateTimeObject(DateFrom, FromTime);
        } else {
            reserveFromDate = null;
        }

        if (ReserveToTime.valueProperty().isNotNull().getValue()) {
            LocalDate ToDate = ReserveToTime.getValue();
            String ToTime = TimeComboBox.getValue().toString();
            reserveToDate = DateClass.getDateTimeObject(ToDate, ToTime);
        } else {
            reserveToDate = null;
        }

        String reservatioNo = ReservationNo.getText();
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        ArrayList<Reservation> newArray = newReserveCtrl.searchReserveForRent(reserveFromDate, reserveToDate, BranchCtrl.getDefaultBranch(), reservatioNo);
        ObservableList<Reservation> slist = FXCollections.observableArrayList(newArray);
        ReservationTable.setItems(slist);
        System.out.println("I am here and it is working");
        ReservationNumber.setCellValueFactory(new PropertyValueFactory("reservationNo"));
        VehicleClass.setCellValueFactory(new PropertyValueFactory("vehicleClass"));
        ReturnDate.setCellValueFactory(new PropertyValueFactory("returnTimeString"));
        CustomerPhone.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
        PickUpDate.setCellValueFactory(new PropertyValueFactory("pickupTimeString"));
    }

    @FXML
    public void DisplayReservationsButtonAction(ActionEvent event) throws ParseException {
        ReservationTable.getItems().clear();
        getList();

    }

    @FXML
    public void CancelReservationButtonAction(ActionEvent event) throws ParseException {
        if (!ReservationTable.getSelectionModel().isEmpty()) {
            Reservation selectedReservation = (Reservation) ReservationTable.getSelectionModel().getSelectedItem();
            //System.out.println(rrr.getReservationNumber());
            if (ReserveCtrl.cancelReserve(selectedReservation)) {
                System.out.println("Reservation Successfully Cancelled");

                ReservationTable.getItems().clear();
                getList();

            } else {
                System.out.println("Reservation Not updated ");
            }
        }

    }

    @FXML
    private void ViewReservationButtonAction(ActionEvent event) throws IOException {

        if (!ReservationTable.getSelectionModel().isEmpty()) {
            Reservation selectedReservation = (Reservation) ReservationTable.getSelectionModel().getSelectedItem();
            RentNavigator.selectedReservation = selectedReservation;
            //System.out.println(rrr.getReservationNumber());
            RentNavigator.loadVista(RentNavigator.ReservationSummaryPage);
        }
    }
}
