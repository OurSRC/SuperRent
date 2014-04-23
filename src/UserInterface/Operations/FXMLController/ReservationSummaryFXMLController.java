/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.BranchCtrl;
import ControlObjects.FinanceCtrl;
import ControlObjects.Reservation;
import ControlObjects.ReserveCtrl;
import ControlObjects.StaffCtrl;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import entity.Staff;
import finance.Price;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReservationSummaryFXMLController implements Initializable {

    @FXML
    private Font x1;
    @FXML
    private TextField PickUpDateTF;
    @FXML
    private TextField ReturnDateTF;
    @FXML
    private TextField VehicleClassTF;
    @FXML
    private TextField VehicleTypeTF;
    @FXML
    private ListView AdditionalEquipmentList;
    @FXML
    private TextField EstimatedCostTF;
    @FXML
    private TextField CreditCardTF;
    @FXML
    private ListView InsuranceList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VehicleClassTF.setText(ReservationNavigator.newReserve.getVehicleClass());
        PickUpDateTF.setText(ReservationNavigator.newReserve.getPickupTime().toString());
        ReturnDateTF.setText(ReservationNavigator.newReserve.getReturnTime().toString());
        System.out.println("Outside Additional Equipments");

        if (ReservationNavigator.newReserve.getEquipmentType().size() != 0) {
            System.out.println("Inside Additional Equipments");
            ObservableList<String> items = FXCollections.observableArrayList(ReservationNavigator.newReserve.getEquipmentType());
            AdditionalEquipmentList.setItems(items);
        }

        if (!ReservationNavigator.newReserve.getInsurance().isEmpty()) {
            ObservableList<String> items = FXCollections.observableArrayList(ReservationNavigator.newReserve.getInsurance());
            InsuranceList.setItems(items);
        }

        FinanceCtrl newFinanceCtrl = new FinanceCtrl();
        int sample = newFinanceCtrl.estimateReservationCost(ReservationNavigator.newReserve);
        EstimatedCostTF.setText(Price.toText(sample));
    }

    @FXML
    private void ConfirmButtonAction(ActionEvent event) {

        ReserveCtrl newReserveCtrl = new ReserveCtrl();
        ReservationNavigator.newReserve.setBranchId(BranchCtrl.getDefaultBranch().getBranchID());
        if (ClerkMainPageNavigator.staff) {
            StaffCtrl staffCtrl = new StaffCtrl();
            Staff staff = staffCtrl.getStaffByUsername(ClerkMainPageNavigator.CurrentUserName);
            ReservationNavigator.newReserve.setStaffId(staff.getStaffId());
        }
        Reservation newReservation = newReserveCtrl.createReserve(ReservationNavigator.newReserve);
        if (newReservation == null) {

        } else {
            System.out.println(newReservation.getReservationNo());
        }
    }

    @FXML
    private void AbortButtonAction(ActionEvent event) {
    }

}
