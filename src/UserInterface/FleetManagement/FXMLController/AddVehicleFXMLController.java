/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.FleetManagement.FXMLController;

import ControlObjects.VehicleCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import SystemOperations.ErrorMsg;
import entity.Vehicle;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class AddVehicleFXMLController implements Initializable {

    @FXML
    private Font x1;
    @FXML
    private TextField ModelTF;
    @FXML
    private TextField PlateNumberTF;
    @FXML
    private TextField ManufacturerTF;
    @FXML
    private Font x2;
    @FXML
    private RadioButton VehicleTypeTruckRB;
    @FXML
    private RadioButton VehicleTypeCarRB;
    @FXML
    private ComboBox VehicleClassCB;
    @FXML
    private DatePicker ManufacturingDatePicker;

    @FXML
    private ToggleGroup VehicleTypeTG;
    @FXML
    private CheckBox NewVehicleCheckBox;

    @FXML
    private TextField OdometerReadingTF;

    /* Variables to store the values in the input field */
    private String vehicleType;
    private String vehicleClass;
    private String plateNumber;
    private Date manufacturingDate;
    private int odometerReading;
    private String Model;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        vehicleType = "CAR";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    @FXML
    private void ConfirmButtonAction(ActionEvent event) throws ParseException, IOException {

        try {
            if (ValidateMandatoryFields()) {
                plateNumber = PlateNumberTF.getText();
                vehicleClass = VehicleClassCB.getSelectionModel().getSelectedItem().toString();
                Model = ModelTF.getText();
                manufacturingDate = DateClass.getDateObject(ManufacturingDatePicker.getValue());
                odometerReading = Integer.parseInt(OdometerReadingTF.getText());
                System.out.println(plateNumber + " " + vehicleClass + " " + Model + " " + odometerReading + " " + manufacturingDate.toString());
                Vehicle newVehicle = new Vehicle(plateNumber, manufacturingDate, Model, odometerReading, 1, Vehicle.STATUS.FORRENT, Vehicle.RENTSTATUS.IDLE, null, vehicleClass, 0);

                VehicleCtrl newVehicleCtrl = new VehicleCtrl();
                Vehicle returnVehicle = newVehicleCtrl.createVehicle(newVehicle);
                System.out.println("I am Vyas");

                if (returnVehicle == null) {
                    DialogFX dialog = new DialogFX(Type.ERROR);
                    dialog.setTitleText("Error");
                    dialog.setMessage("     " + ErrorMsg.translateError(ErrorMsg.getLastError()) + "    ");
                    dialog.showDialog();
                } else {
                    DialogFX dialog = new DialogFX(Type.INFO);
                    dialog.setTitleText("Success");
                    dialog.setMessage("Vehicle " + plateNumber + " is successfully created");
                    dialog.showDialog();
                    VehicleNavigator.loadVista(VehicleNavigator.VEHICLEMAINPAGE);
                }
            } else {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage(" Pleas enter the Mandatory Fields");
                dialog.showDialog();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Improper Input provided");
        }
    }

    @FXML
    private void BeckToSearchButtonAction(ActionEvent event) throws IOException {
        VehicleNavigator.loadVista(VehicleNavigator.VEHICLEMAINPAGE);
    }

    @FXML
    private void VehicleTypeTruckRBAction(ActionEvent event) {
        vehicleType = "TRUCK";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getTruckType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);

    }

    @FXML
    private void VehicleTypeCarRBAction(ActionEvent event) {
        vehicleType = "CAR";
        VehicleCtrl vehicleControl = new VehicleCtrl();
        ObservableList<String> list = FXCollections.observableArrayList(vehicleControl.getCarType());
        VehicleClassCB.getItems().clear();
        VehicleClassCB.setItems(list);
    }

    public boolean ValidateMandatoryFields() {
        if (!PlateNumberTF.getText().equals("")
                && !ModelTF.getText().equals("")
                && !VehicleClassCB.valueProperty().isNull().getValue()
                && !ManufacturingDatePicker.valueProperty().isNull().getValue()) {
            return true;
        } else {
            return false;
        }
    }

}
