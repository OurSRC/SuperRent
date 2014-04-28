/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Login.FXML;

import ControlObjects.PieChatCtrl;
import ControlObjects.Reservation;
import ControlObjects.ReserveCtrl;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class PendingReservationChartController implements Initializable {
    @FXML
    private TableView ReservationTable;
    @FXML
    private TableColumn<?, ?> ReservationNumberColumn;
    @FXML
    private TableColumn<?, ?> VehicleClassColumn;
    @FXML
    private TableColumn<?, ?> PickUpDateColumn;
    @FXML
    private TableColumn<?, ?> CustomerPhoneColumn;
    @FXML
    private TableColumn<?, ?> CustomerNameColumn;
    @FXML
    private TableColumn<?, ?> ReturnDateColumn;
    @FXML
    private TableColumn<?, ?> EstimatedCostColumn;
    @FXML
    private PieChart ReservationPieChart;

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
        ArrayList<AbstractMap.SimpleEntry<String, Integer>> pieChartArray = null;

        pieChartArray = PieChatCtrl.getPendingReservationCount(1, null);

        for (AbstractMap.SimpleEntry<String, Integer> element : pieChartArray) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (AbstractMap.SimpleEntry<String, Integer> element : pieChartArray) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
            pieChartData.add(new PieChart.Data(element.getKey(), Double.parseDouble(element.getValue().toString())));
        }
        ReservationPieChart.setData(pieChartData);
        ReservationPieChart.setAnimated(true);
        ReservationPieChart.setTitle("Reservation's");
        ReservationPieChart.setLabelLineLength(10);
        ReservationPieChart.setLegendSide(Side.BOTTOM);
    }

    public void populatePendingReservationTable() {
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        ArrayList<Reservation> searchPendingReservation = ReserveCtrl.searchPendingReservation(1, null);
        System.out.println("Array List size : " + searchPendingReservation.size());
        ObservableList<Reservation> slist = FXCollections.observableArrayList(searchPendingReservation);
        ReservationTable.setItems(slist);

        ReservationNumberColumn.setCellValueFactory(new PropertyValueFactory("reservationNo"));
        VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("vehicleClass"));
        ReturnDateColumn.setCellValueFactory(new PropertyValueFactory("returnTimeString"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
        PickUpDateColumn.setCellValueFactory(new PropertyValueFactory("pickupTimeString"));
        EstimatedCostColumn.setCellValueFactory(new PropertyValueFactory("estimation"));

    }
 
    
}
