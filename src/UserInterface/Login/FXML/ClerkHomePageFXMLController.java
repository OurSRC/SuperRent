/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Login.FXML;

import SystemOperations.PieChatCtrl;
import Operate.Reservation;
import Operate.ReserveCtrl;
import java.io.IOException;
import static java.lang.Math.E;
import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ClerkHomePageFXMLController implements Initializable {

    private PieChart ReservationPieChart;
    private TableView ReservationTable;
    private TableColumn ReservationNumberColumn;
    private TableColumn VehicleClassColumn;
    private TableColumn PickUpDateColumn;
    private TableColumn ReturnDateColumn;
    private TableColumn EstimatedCostColumn;
    private TableColumn CustomerPhoneColumn;
    private TableColumn CustomerNameColumn;
    @FXML
    private ToggleGroup PieChartToggleGroup;
    @FXML
    private StackPane HomePageStackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
                // TODO

            /* ObservableList<PieChart.Data> pieChartData =
             FXCollections.observableArrayList(
             new PieChart.Data("Grapefruit", 13),
             new PieChart.Data("Oranges", 25),
             new PieChart.Data("Plums", 10),
             new PieChart.Data("Pears", 22),
             new PieChart.Data("Apples", 30));*/
            System.out.println("I am here");

                //populateReservePieChart();
            //populatePendingReservationTable();
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Login/FXML/PendingReservationChart.fxml"));
            Pane registerPane = (Pane) myLoader.load();
            HomePageStackPane.getChildren().add(registerPane);
        } catch (IOException ex) {
            Logger.getLogger(ClerkHomePageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        ReturnDateColumn.setCellValueFactory(new PropertyValueFactory("returnTime"));
        CustomerPhoneColumn.setCellValueFactory(new PropertyValueFactory("customerPhone"));
        CustomerNameColumn.setCellValueFactory(new PropertyValueFactory("customerName"));
        PickUpDateColumn.setCellValueFactory(new PropertyValueFactory("pickupTime"));
        EstimatedCostColumn.setCellValueFactory(new PropertyValueFactory("estimation"));

    }

    @FXML
    private void ReturnToggleButton(ActionEvent event) throws IOException {
        HomePageStackPane.getChildren().clear();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Login/FXML/PendingReturnFXML.fxml"));
        Pane registerPane = (Pane) myLoader.load();
        HomePageStackPane.getChildren().add(registerPane);
        System.out.println("I am here");
    }

    @FXML
    private void ReservationToggleButton(ActionEvent event) throws IOException {
        HomePageStackPane.getChildren().clear();
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Login/FXML/PendingReservationChart.fxml"));
        Pane registerPane = (Pane) myLoader.load();
        HomePageStackPane.getChildren().add(registerPane);
    }

}
