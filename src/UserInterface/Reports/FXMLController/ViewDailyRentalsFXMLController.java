/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Reports.FXMLController;

import ControlObjects.RentCtrl;
import ControlObjects.VehicleCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import entity.Rent;
import entity.Staff;
import entity.Vehicle;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import report.PdfGen;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewDailyRentalsFXMLController implements Initializable {
    @FXML
    private TableView DailyRentalsTable;
    @FXML
    private Label DateLabel;
    @FXML
    private Button SearchButton;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private Label TotalRentalsLabel;
    //@FXML
    //private TextField TotalRentalsTF;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private DatePicker SearchDateDP;
    @FXML
    private Font x1;

    @FXML
    private Label RentalDateLabel;
    @FXML
    private ComboBox BranchCB;
    @FXML
    private Label totalRentalsForTheDay;

    private Date dateValue;
    private LocalDate localDateValue;
    private int branchID;
    private int totalNumberOfRentals;
    @FXML
    private TableColumn PlateNumberColumn;
    @FXML
    private TableColumn VehicleClassColoumn;
    @FXML
    private TableColumn ModelColumn;
    @FXML
    private TableColumn ManufacturingYearColumn;
    @FXML
    private TableColumn BranchIDColumn;
    private ArrayList<Vehicle> vehicleArrayList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TotalRentalsLabel.setVisible(false);
        PrintPDFButton.setDisable(true);
    }

    @FXML
    private void SearchDailyRentalsAction(ActionEvent event) {
        if (ValidateInput()) {
            localDateValue = SearchDateDP.getValue();
            dateValue = DateClass.ConvertLocalDatetoDate(localDateValue);
            if (!BranchCB.valueProperty().isNull().getValue()) {
                switch (BranchCB.getSelectionModel().getSelectedItem().toString()) {
                    case "Branch 1":
                        branchID = 1;
                        break;
                       
                }
            } else {
                branchID = 0;
            }
            
            populateSearchTable();
            
       
        } else {

            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter the Date");
            dialog.showDialog();
        }
    }

    public boolean ValidateInput() {
        if (SearchDateDP.valueProperty().isNotNull().getValue()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
        String pdfName = "Rentals Report " + DateClass.getJustDateFromDateObject(dateValue);
        PdfGen.genVehicleReport(vehicleArrayList, pdfName);
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
    private void SearchDateAction(ActionEvent event) {
    }

    private void populateSearchTable() {

        RentCtrl newRentCtrl = new RentCtrl();
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        ArrayList<Rent> rentArrayList = newRentCtrl.getRentsByDate(dateValue, branchID);
        vehicleArrayList = new ArrayList<>();
        for (Rent rent : rentArrayList) {
            vehicleArrayList.add(newVehicleCtrl.getVehicleByVehicleNo(rent.getVehicleNo()));
        }
        
        //Enabling - Disabling the PrintPDF button
        if (vehicleArrayList.size() > 0) {
            PrintPDFButton.setDisable(false);
        } else {
            PrintPDFButton.setDisable(true);
        }
        
        // Setting the total number of rented vehicles label
        totalNumberOfRentals = vehicleArrayList.size();
        totalRentalsForTheDay.setText(String.valueOf(totalNumberOfRentals));
        TotalRentalsLabel.setVisible(true);
        
        
        DailyRentalsTable.getItems().clear();
        ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList(vehicleArrayList);
        DailyRentalsTable.setItems(vehicleObservableList);
        PlateNumberColumn.setCellValueFactory(new PropertyValueFactory("plateNo"));
        ModelColumn.setCellValueFactory(new PropertyValueFactory("mode"));
        VehicleClassColoumn.setCellValueFactory(new PropertyValueFactory("className"));
        ManufacturingYearColumn.setCellValueFactory(new PropertyValueFactory("manufactureDate"));
        BranchIDColumn.setCellValueFactory(new PropertyValueFactory("branchId"));

    }

    @FXML
    private void BranchCBAction(ActionEvent event) {
    }
}
