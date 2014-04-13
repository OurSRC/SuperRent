/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.FleetManagement.FXMLController;

import ControlObjects.BranchCtrl;
import ControlObjects.VehicleCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import entity.VehicleClass;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class VehicleAvailabilityFXMLController implements Initializable {

    @FXML
    private ComboBox VehicleType;
    @FXML
    private TableColumn HourlyRateColumn;
    @FXML
    private TableColumn DailyRateColumn;
    @FXML
    private TableColumn WeeklyRateColumn;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn VehicleTypeColumn;
    @FXML
    private DatePicker DateFromDatePicker;
    @FXML
    private ComboBox DateFromTime;
    @FXML
    private ComboBox DateToTime;
    @FXML
    private DatePicker DateToDatePicker;
    @FXML
    private TableView VehicleClassTable;

    /* Variables to store */
    public Date PickUpDate;
    public Date ReturnDate;
    public String vehicleType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    public void DisplayAvailableVehicleAction(ActionEvent event) throws ParseException {

        if (ValidateInput()) {
            LocalDate DateFrom = DateFromDatePicker.getValue();
            String FromTime = DateFromTime.getValue().toString();
            PickUpDate = DateClass.getDateTimeObject(DateFrom, FromTime);

            LocalDate ToDate = DateToDatePicker.getValue();
            String ToTime = DateToTime.getValue().toString();
            ReturnDate = DateClass.getDateTimeObject(ToDate, ToTime);

            vehicleType = VehicleType.getValue().toString();
            System.out.println("Passing Parameters from VehicleAvailabilityFXMLController to Vehicle Control");
            System.out.println("Vehicle Type : " + vehicleType);
            System.out.println("PickUpDate : " + PickUpDate.toString());
            System.out.println("ReturnDate : " + ReturnDate.toString());
            System.out.println("Return Type : ArrayList<VehicleClass>");

            if (ReturnDate.after(PickUpDate) && PickUpDate.compareTo(ReturnDate) != 0) {
                VehicleClass.TYPE type;
                if (vehicleType.compareTo("Car") == 0) {
                    type = VehicleClass.TYPE.Car;
                } else {
                    type = VehicleClass.TYPE.Truck;
                }

                VehicleCtrl vehicleControl = new VehicleCtrl();
                ArrayList<String> AvailableVehicleTypes = vehicleControl.getVehicleAvailability(type, PickUpDate, ReturnDate, BranchCtrl.getDefaultBranch());

                ArrayList<VehicleClass> VehicleClassArray = new ArrayList();
                System.out.println(AvailableVehicleTypes.size() + " Size of the ARraylist");
                for (int i = 0; i < AvailableVehicleTypes.size(); i++) {
                    VehicleClassArray.add(vehicleControl.findVehicleClass(AvailableVehicleTypes.get(i)));
                }
                ObservableList<VehicleClass> list = FXCollections.observableArrayList(VehicleClassArray);
                VehicleClassTable.getItems().clear();
                VehicleClassTable.setItems(list);

                VehicleTypeColumn.setCellValueFactory(new PropertyValueFactory("vehicleType"));
                VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("className"));
                HourlyRateColumn.setCellValueFactory(new PropertyValueFactory("hourlyPrice"));
                WeeklyRateColumn.setCellValueFactory(new PropertyValueFactory("weeklyPrice"));
                DailyRateColumn.setCellValueFactory(new PropertyValueFactory("dailyPrice"));

            } else {
                System.out.println("Invalid Dates ");
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Invalid Dates Entered");
                dialog.showDialog();
            }
        } else {
            System.out.println("Please enter the Mandatory Fields");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter all the Mandatory Values");
            dialog.showDialog();
        }
    }

    public void ReserveVehicleActionButton(ActionEvent event) throws IOException {
        if (!VehicleClassTable.getSelectionModel().isEmpty()) {
            VehicleClass selectedVehicleClass = (VehicleClass) VehicleClassTable.getSelectionModel().getSelectedItem();
            ReservationNavigator.newReserve.setPickupTime(PickUpDate);
            ReservationNavigator.newReserve.setReturnTime(ReturnDate);
            ReservationNavigator.newReserve.setVehicleClass(selectedVehicleClass.getClassName());
            ReservationNavigator.newReserve.setHourlyPrice(selectedVehicleClass.getHourlyPrice());
            ReservationNavigator.newReserve.setWeeklyPrice(selectedVehicleClass.getWeeklyPrice());
            ReservationNavigator.newReserve.setDailyPrice(selectedVehicleClass.getDailyPrice());

            System.out.println("Successfully stored values into Reservation object in ReservationNavigator");
            ReservationNavigator.loadVista(ReservationNavigator.ADDITIONALEQUIPMENTS);
        } else {
            System.out.println("Please enter the Mandatory Fields");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please select a vehicle Class to proceed for Reservation");
            dialog.showDialog();
        }
    }

    public boolean ValidateInput() {
        if (DateFromDatePicker.valueProperty().isNotNull().getValue()
                && DateToDatePicker.valueProperty().isNotNull().getValue()
                && VehicleType.valueProperty().isNotNull().getValue()
                && DateFromTime.valueProperty().isNotNull().getValue()
                && DateToTime.valueProperty().isNotNull().getValue()) {
            return true;
        } else {
            return false;
        }
    }

}
