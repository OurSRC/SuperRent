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
public class PendingReturnFXMLController implements Initializable {
    @FXML
    private TableView ReturnTable;
    @FXML
    private TableColumn<?, ?> ReservationNumberColumn;
    @FXML
    private TableColumn<?, ?> VehicleClassColumn;
    @FXML
    private TableColumn<?, ?> ReturnDateColumn;
    @FXML
    private TableColumn<?, ?> CustomerPhoneColumn;
    @FXML
    private TableColumn<?, ?> CustomerNameColumn;
    @FXML
    private PieChart ReturnPieChart;

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

        populateRentPieChart();
        populatePendingRentTable();

    }

    public void populateRentPieChart() {
        ArrayList<AbstractMap.SimpleEntry<String, Integer>> pieChartArray = null;

        pieChartArray = PieChatCtrl.getPendingReturnCount(1, null);

        for (AbstractMap.SimpleEntry<String, Integer> element : pieChartArray) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (AbstractMap.SimpleEntry<String, Integer> element : pieChartArray) {
            System.out.println("Key:" + element.getKey() + " Value: " + element.getValue().toString());
            pieChartData.add(new PieChart.Data(element.getKey(), Double.parseDouble(element.getValue().toString())));
        }
        ReturnPieChart.setData(pieChartData);
        ReturnPieChart.setAnimated(true);
        ReturnPieChart.setTitle("Return's");
        ReturnPieChart.setLabelLineLength(10);
        ReturnPieChart.setLegendSide(Side.BOTTOM);
    }

    public void populatePendingRentTable() {
        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        ArrayList<Reservation> searchPendingReservation = ReserveCtrl.searchNotReturnedReservation(1, null);
        System.out.println("Array List size : " + searchPendingReservation.size());
        ObservableList<Reservation> slist = FXCollections.observableArrayList(searchPendingReservation);
        ReturnTable.setItems(slist);

        ReservationNumberColumn.setCellValueFactory(new PropertyValueFactory("reservationNo"));
        VehicleClassColumn.setCellValueFactory(new PropertyValueFactory("vehicleClass"));
        ReturnDateColumn.setCellValueFactory(new PropertyValueFactory("returnTime"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));

    }
     
    
}
