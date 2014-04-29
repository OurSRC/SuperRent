/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Reports.FXMLController;

import Operate.Reservation;
import Operate.ReserveCtrl;
import SystemOperations.DateClass;
import java.awt.Desktop;
import java.io.File;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import SystemOperations.PdfGen;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewVehiclesNotReturnedFXMLController implements Initializable {

    @FXML
    private TableView NotReturnedTable;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private Font x1;
    @FXML
    private TableColumn ContractNoColumn;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn ReturnDateColumn;
    @FXML
    private TableColumn CustomerNameColumn;
    @FXML
    private TableColumn CustomerPhoneColumn;
    @FXML
    private TableColumn CustomerIDColumn;

    private Date date;
    private ArrayList<Reservation> notReturnedArrayList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date = new Date();
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        notReturnedArrayList = newReserveCtrl.searchNotReturnedReservation(0, date);
        NotReturnedTable.getItems().clear();
        if (!notReturnedArrayList.isEmpty()) {
            
             //Enabling - Disabling the PrintPDF button
                if (notReturnedArrayList.size() > 0) {
                    PrintPDFButton.setDisable(false);
                } else {
                    PrintPDFButton.setDisable(true);
                }

            ObservableList notReturnedObservableList = FXCollections.observableArrayList(notReturnedArrayList);
            NotReturnedTable.setItems(notReturnedObservableList);
            ContractNoColumn.setCellValueFactory(new PropertyValueFactory("contractNo"));
            VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("vehicleClass"));
            ReturnDateColumn.setCellValueFactory(new PropertyValueFactory("returnTime"));
            CustomerIDColumn.setCellValueFactory(new PropertyValueFactory("customerId"));
            CustomerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
            CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        }
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
         String pdfName = "Not Returned Vehicles, " + DateClass.getJustDateFromDateObject(date);
        PdfGen.genVehicleNotReturnReport(notReturnedArrayList, pdfName);
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(pdfName + ".pdf");
                Desktop.getDesktop().open(myFile);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
