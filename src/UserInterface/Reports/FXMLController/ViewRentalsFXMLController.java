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
import com.itextpdf.text.DocumentException;
import entity.Rent;
import entity.Vehicle;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import report.PdfGen;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewRentalsFXMLController implements Initializable {

    @FXML
    private Button PrintPDFButton;
    @FXML
    private Label DateLabel;
    @FXML
    private Label ToLabel;
    @FXML
    private Label FromLabel;
    @FXML
    private Button SearchButton;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private DatePicker ToDateDP;
    @FXML
    private DatePicker FromDateDP;
    @FXML
    private Font x1;
    @FXML
    private ComboBox BranchCB;
    @FXML
    private TableView RentalTable;
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

    private Date fromDateValue;
    private Date toDateValue;
    private LocalDate fromLocalDateValue;
    private LocalDate toLocalDateValue;
    private int branchID;
    private ArrayList<Vehicle> vehicleArrayList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PrintPDFButton.setDisable(true);
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) throws DocumentException, FileNotFoundException {
        String pdfName = "Rentals Report from " + DateClass.getJustDateFromDateObject(fromDateValue) + " to " + DateClass.getJustDateFromDateObject(toDateValue);
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
    private void SearchButtonAction(ActionEvent event) {
        if (ValidateInput()) {
            fromLocalDateValue = FromDateDP.getValue();
            toLocalDateValue = ToDateDP.getValue();
            fromDateValue = DateClass.ConvertLocalDatetoDate(fromLocalDateValue);
            toDateValue = DateClass.ConvertLocalDatetoDate(toLocalDateValue);
            if (toDateValue.compareTo(fromDateValue) > 0) {
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
                System.out.println("Please enter the Date");
                DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage("To Date should be after than From Date");
                dialog.showDialog();
            }
        } else {
            System.out.println("Please enter the Date");
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter the Dates");
            dialog.showDialog();
        }

    }

    public boolean ValidateInput() {
        if (FromDateDP.valueProperty().isNotNull().getValue()
                && ToDateDP.valueProperty().isNotNull().getValue()) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void BranchCBAction(ActionEvent event) {
    }

    private void populateSearchTable() {
        RentCtrl newRentCtrl = new RentCtrl();
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        ArrayList<Rent> rentArrayList = newRentCtrl.getRentByDates(fromDateValue, toDateValue, branchID);
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

        RentalTable.getItems().clear();
        ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList(vehicleArrayList);
        RentalTable.setItems(vehicleObservableList);
        PlateNumberColumn.setCellValueFactory(new PropertyValueFactory("plateNo"));
        ModelColumn.setCellValueFactory(new PropertyValueFactory("mode"));
        VehicleClassColoumn.setCellValueFactory(new PropertyValueFactory("className"));
        ManufacturingYearColumn.setCellValueFactory(new PropertyValueFactory("manufactureDate"));
        BranchIDColumn.setCellValueFactory(new PropertyValueFactory("branchId"));
    }

}
