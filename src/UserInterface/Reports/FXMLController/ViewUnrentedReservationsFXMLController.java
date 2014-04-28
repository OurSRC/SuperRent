/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import ControlObjects.Reservation;
import ControlObjects.ReserveCtrl;
import SystemOperations.DateClass;
import entity.ReservationInfo;
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
import report.PdfGen;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewUnrentedReservationsFXMLController implements Initializable {

    @FXML
    private TableView UnrentedTable;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private Font x1;
    @FXML
    private TableColumn ReservationnoColumn;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn PickupDateColumn;
    @FXML
    private TableColumn ReturnDateColumn;
    @FXML
    private TableColumn CusIDColumn;
    @FXML
    private TableColumn CustomerNameColumn;
    @FXML
    private TableColumn CustomerPhoneColumn;
    
    private Date date;
    private ArrayList<Reservation> reservationArrayList;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        date = new Date();
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        reservationArrayList = newReserveCtrl.searchPendingReservation(0, date);
        UnrentedTable.getItems().clear();
        
        //Enabling - Disabling the PrintPDF button
                if (reservationArrayList.size() > 0) {
                    PrintPDFButton.setDisable(false);
                } else {
                    PrintPDFButton.setDisable(true);
                }
        
        ObservableList reservationObservableList = FXCollections.observableArrayList(reservationArrayList);
        UnrentedTable.setItems(reservationObservableList);
        ReservationnoColumn.setCellValueFactory(new PropertyValueFactory("reservationNo"));
        VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("vehicleClass"));
        PickupDateColumn.setCellValueFactory(new PropertyValueFactory("pickupTime"));
        ReturnDateColumn.setCellValueFactory(new PropertyValueFactory("returnTime"));
        CusIDColumn.setCellValueFactory(new PropertyValueFactory("customerId"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        
        
        
    }    

    @FXML
    private void PrintPDFAction(ActionEvent event) {
         String pdfName = "Reservation not Rented, " + DateClass.getJustDateFromDateObject(date);
        PdfGen.genUnrentedReservation(reservationArrayList, pdfName);
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
