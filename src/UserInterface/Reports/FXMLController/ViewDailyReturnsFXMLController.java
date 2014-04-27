/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import ControlObjects.RentCtrl;
import ControlObjects.ReturnCtrl;
import ControlObjects.VehicleCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import entity.Rent;
import entity.Return;
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
import javafx.scene.text.Font;
import report.PdfGen;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewDailyReturnsFXMLController implements Initializable {

    @FXML
    private Label PrintDateLabel;
    public DatePicker SearchDateDP;
    String DateValue;
    @FXML
    private Font x1;
    @FXML
    private ComboBox BranchCB;
    @FXML
    private Button SearchButton;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private TableView DailyReturnsTable;
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
    
    private Date dateValue;
    private LocalDate localDateValue;
    private int branchID;
    private int totalNumberOfReturns;
    @FXML
    private Label TotalReturnsLabel;
    @FXML
    private Label totalReturnsForTheDay;
    private ArrayList<Vehicle> vehicleArrayList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         TotalReturnsLabel.setVisible(false);
         PrintPDFButton.setDisable(true);
    }    
    @FXML
    private void SearchDailyReturnsAction(ActionEvent event) {
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
        if (SearchDateDP.valueProperty().isNotNull().getValue())
        {
            return true;
        } else {
            return false;
        }
    }
    
    @FXML
    private void PrintPDFAction(ActionEvent event) {
        String pdfName = "Returns Report " + DateClass.getJustDateFromDateObject(dateValue);
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

        ReturnCtrl newReturnCtrl = new ReturnCtrl();
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        RentCtrl newRentCtrl = new RentCtrl();
        ArrayList<Return> returnArrayList = newReturnCtrl.getRerurnsByDate(dateValue, branchID);
        vehicleArrayList = new ArrayList<>();
        for (Return returnObject : returnArrayList) {
            Rent rent = newRentCtrl.getRentByContractNumber(returnObject.getContractNo());
            vehicleArrayList.add(newVehicleCtrl.getVehicleByVehicleNo(rent.getVehicleNo()));
        }
        
         //Enabling - Disabling the PrintPDF button
        if (vehicleArrayList.size() > 0) {
            PrintPDFButton.setDisable(false);
        } else {
            PrintPDFButton.setDisable(true);
        }
        
        // Setting the total number of rented vehicles label
        totalNumberOfReturns = vehicleArrayList.size();
        totalReturnsForTheDay.setText(String.valueOf(totalNumberOfReturns));
        TotalReturnsLabel.setVisible(true);
        
        DailyReturnsTable.getItems().clear();
        ObservableList<Vehicle> vehicleObservableList = FXCollections.observableArrayList(vehicleArrayList);
        DailyReturnsTable.setItems(vehicleObservableList);
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
