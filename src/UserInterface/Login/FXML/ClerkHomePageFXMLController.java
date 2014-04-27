/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Login.FXML;

import ControlObjects.PieChatCtrl;
import ControlObjects.Reservation;
import ControlObjects.ReserveCtrl;
import static java.lang.Math.E;
import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ClerkHomePageFXMLController implements Initializable {

    @FXML
    private PieChart ReservationPieChart;
    @FXML
    private TableView ReservationTable;
    @FXML
    private TableColumn ReservationNumberColumn;
    @FXML
    private TableColumn VehicleClassColumn;
    @FXML
    private TableColumn PickUpDateColumn;
    @FXML
    private TableColumn ReturnDateColumn;
    @FXML
    private TableColumn EstimatedCostColumn;
    @FXML
    private TableView ReturnTable;
    @FXML
    private TableColumn ContractColumn;
    @FXML
    private TableColumn VehicleClassForReturnColumn;
    @FXML
    private TableColumn VehiclePlateColumn;
    @FXML
    private TableColumn CustomerPhoneColumn;
    @FXML
    private TableColumn CustomerNameColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        /* ObservableList<PieChart.Data> pieChartData =
         FXCollections.observableArrayList(
         new PieChart.Data("Grapefruit", 13),
         new PieChart.Data("Oranges", 25),
         new PieChart.Data("Plums", 10),
         new PieChart.Data("Pears", 22),
         new PieChart.Data("Apples", 30));*/
        System.out.println("I am here");

        populateReservePieChart();
        populatePendingReservationTable();

    }

    public void populateReservePieChart() {
        ArrayList<SimpleEntry<String, Integer>> pieChartArray = null;

        pieChartArray = PieChatCtrl.getPendingReservationCount(1, null);

        for (SimpleEntry<String, Integer> element : pieChartArray) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (SimpleEntry<String, Integer> element : pieChartArray) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
            pieChartData.add(new PieChart.Data(element.getKey(), Double.parseDouble(element.getValue().toString())));
        }
        ReservationPieChart.setData(pieChartData);
        ReservationPieChart.setAnimated(true);
        ReservationPieChart.setTitle("Reservation'ss");
    }

    public void populatePendingReservationTable() {
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        ArrayList<Reservation> searchPendingReservation = ReserveCtrl.searchPendingReservation(1, null);
        System.out.println("Array List size : " + searchPendingReservation.size());
        ObservableList<Reservation> slist = FXCollections.observableArrayList(searchPendingReservation);
        ReservationTable.setItems(slist);

        ReservationNumberColumn.setCellValueFactory(new PropertyValueFactory("reservationNo"));
        VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("vehicleClass"));
        ReturnDateColumn.setCellValueFactory(new PropertyValueFactory("returnTime"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
        PickUpDateColumn.setCellValueFactory(new PropertyValueFactory("pickupTime"));
        EstimatedCostColumn.setCellValueFactory(new PropertyValueFactory("estimation"));

    }

}
