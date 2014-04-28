/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Reports.FXMLController;

import ControlObjects.BranchCtrl;
import ControlObjects.VehicleCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import entity.VehicleClass;
import java.awt.Desktop;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import report.PdfGen;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewAvailableVehiclesFXMLController implements Initializable {

    @FXML
    private Label VehicleCategoryLabel;
    @FXML
    private Button SearchButton;
    @FXML
    private Label PickUpDateLabel;
    @FXML
    private Label RetrunDateLabel;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private DatePicker PickUpDateDP;
    @FXML
    private DatePicker ReturnDateDP;
    @FXML
    private ComboBox TypeOfVehicle;
    @FXML
    private TableView AvailableVehiclesTable;
    @FXML
    private TableColumn VehicleTypeColumn;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn HourlyRateColumn;
    @FXML
    private TableColumn DailyRateColumn;
    @FXML
    private TableColumn WeeklyRateColumn;
    @FXML
    private ComboBox DateFromTime;
    @FXML
    private ComboBox DateToTime;

    /* Variables to store */
    private Date PickUpDate;
    private Date ReturnDate;
    private String vehicleType;
    private ArrayList<VehicleClass> VehicleClassArray;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ViewAvailableVehiclesAction(ActionEvent event) throws ParseException {
        if (ValidateInput()) {
            LocalDate DateFrom = PickUpDateDP.getValue();
            String FromTime = DateFromTime.getValue().toString();
            PickUpDate = DateClass.getDateTimeObject(DateFrom, FromTime);

            LocalDate ToDate = ReturnDateDP.getValue();
            String ToTime = DateToTime.getValue().toString();
            ReturnDate = DateClass.getDateTimeObject(ToDate, ToTime);

            vehicleType = TypeOfVehicle.getValue().toString();
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
                ArrayList<String> AvailableVehicleTypes = vehicleControl.getAvailableVehicleClasses(type, PickUpDate, ReturnDate, BranchCtrl.getDefaultBranch());

                VehicleClassArray = new ArrayList();
                for (int i = 0; i < AvailableVehicleTypes.size(); i++) {
                    VehicleClassArray.add(vehicleControl.findVehicleClass(AvailableVehicleTypes.get(i)));
                }

                //Enabling - Disabling the PrintPDF button
                if (VehicleClassArray.size() > 0) {
                    PrintPDFButton.setDisable(false);
                } else {
                    PrintPDFButton.setDisable(true);
                }

                ObservableList<VehicleClass> list = FXCollections.observableArrayList(VehicleClassArray);
                AvailableVehiclesTable.getItems().clear();
                AvailableVehiclesTable.setItems(list);

                VehicleTypeColumn.setCellValueFactory(new PropertyValueFactory("vehicleType"));
                VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("className"));
                HourlyRateColumn.setCellValueFactory(new PropertyValueFactory("hourlyPrice"));
                WeeklyRateColumn.setCellValueFactory(new PropertyValueFactory("weeklyPrice"));
                DailyRateColumn.setCellValueFactory(new PropertyValueFactory("dailyPrice"));

            } else {
                System.out.println("Invalid Dates ");
                DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("Invalid Dates Entered");
                dialog.showDialog();
            }
        } else {
            System.out.println("Please enter the Mandatory Fields");
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter all the Mandatory Values");
            dialog.showDialog();
        }

        System.out.println("I am inside this");
    }

    public boolean ValidateInput() {
        if (PickUpDateDP.valueProperty().isNotNull().getValue()
                && ReturnDateDP.valueProperty().isNotNull().getValue()
                && TypeOfVehicle.valueProperty().isNotNull().getValue()
                && DateFromTime.valueProperty().isNotNull().getValue()
                && DateToTime.valueProperty().isNotNull().getValue()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
        String pdfName = "Available Vehicles between " + DateClass.getJustDateFromDateObject(PickUpDate) + 
                " to " + DateClass.getJustDateFromDateObject(ReturnDate);
        PdfGen.genAvailableVehicleClassReport(VehicleClassArray, pdfName);
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(pdfName + ".pdf");
                Desktop.getDesktop().open(myFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void PickUpDateAction(ActionEvent event) {
    }

    @FXML
    private void ReturnDateAction(ActionEvent event) {
    }

}
