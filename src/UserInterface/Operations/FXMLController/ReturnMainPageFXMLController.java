/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.CustomerCtrl;
import ControlObjects.RentCtrl;
import ControlObjects.Reservation;
import ControlObjects.ReserveCtrl;
import ControlObjects.ReturnCtrl;
import ControlObjects.VehicleCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import entity.Customer;
import entity.Rent;
import entity.Return;
import entity.Vehicle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReturnMainPageFXMLController implements Initializable {

    @FXML
    private StackPane ReturnInnerPane;
    public TextField CustomerNameTF;
    public TextField CustomerPhoneTF;
    public TextField VehicleTypeTF;
    public Label CustomerNumberLabel;
    public Label VechicleTypeLabel;
    public Label VehicleNumberLabel;
    public Label RentStartDateLabel;
    public Label RentEndDateLabel;
    public TextField RentalAgreementTF;

    public Pane RentalPane;
    public Pane BottomPane;
    @FXML
    private TextField VehicleModelTF;
    @FXML
    private TextField VehiclePlateTF;
    @FXML
    private TextField RentStartDate;
    @FXML
    private TextField ReservationNumberTF;
    @FXML
    private ListView AdditionalEquipmentList;
    @FXML
    private ListView InsuranceList;

    /* Variables to store the Parameters */
    private Customer rentCustomer;
    private Vehicle rentedVehicle;
    private Reservation rentReservation;
    private Rent searchRent;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void SearchRentButtonAction(ActionEvent event) {

        RentCtrl newRentCtrl = new RentCtrl();
        searchRent = newRentCtrl.getRentByContractNumber(Integer.parseInt(RentalAgreementTF.getText()));

        if (searchRent != null) {
            ReturnCtrl newReturnCtrl = new ReturnCtrl();
            Return newReturn = newReturnCtrl.getReturnByContractNumber(searchRent.getContractNo());
            if (newReturn == null) {
                RentalPane.setVisible(true);

                System.out.println(searchRent.getContractNo());
                ReserveCtrl newReserveCtrl = new ReserveCtrl();
                rentReservation = newReserveCtrl.getReserve(searchRent.getReservationInfold());
                System.out.println(rentReservation.getVehicleClass());
                CustomerCtrl newCustomerCtrl = new CustomerCtrl();
                rentCustomer = newCustomerCtrl.getCustomerById(rentReservation.getCustomerId());

                VehicleCtrl newVehicleCtrl = new VehicleCtrl();
                rentedVehicle = newVehicleCtrl.getVehicleByVehicleNo(searchRent.getVehicleNo());

                ReservationNumberTF.setText(rentReservation.getReservationNo());
                VehiclePlateTF.setText(rentedVehicle.getPlateNo());
                RentStartDate.setText(searchRent.getTime().toString());
                CustomerPhoneTF.setText(rentCustomer.getPhone());
                VehicleModelTF.setText(rentedVehicle.getMode());

                if (rentReservation.getEquipmentType().size() != 0) {
                    System.out.println("Inside Additional Equipments");
                    ObservableList<String> items = FXCollections.observableArrayList(rentReservation.getEquipmentType());
                    AdditionalEquipmentList.setItems(items);
                }

                if (!rentReservation.getInsurance().isEmpty()) {
                    ObservableList<String> items = FXCollections.observableArrayList(rentReservation.getInsurance());
                    InsuranceList.setItems(items);
                }

                /*FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), RentalPane);
                 FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(500), BottomPane);
                 fadeTransition1.setFromValue(0.0);
                 fadeTransition1.setToValue(1.0);

                 fadeTransition.setFromValue(0.0);
                 fadeTransition.setToValue(1.0);
                 fadeTransition.play();
                 fadeTransition1.play();*/
                FadeTransitionMethod(RentalPane);
                FadeTransitionMethod(BottomPane);
            } else {
                System.out.println("Rent Already Returned");
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Invalid Rent");
                dialog.setMessage("Rent Already Returned");
                dialog.showDialog();
            }
        } else {
            System.out.println("Invalid Contract number");
            DialogFX dialog = new DialogFX(Type.ERROR);
            dialog.setTitleText("Invalid Rent");
            dialog.setMessage("Invalid Contract number");
            dialog.showDialog();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        /* CustomerNameTF.setVisible(false);
         CustomerPhoneTF.setVisible(false);
         VehicleTypeTF.setVisible(false);
         CustomerNumberLabel.setVisible(false);
         VechicleTypeLabel.setVisible(false);
         VehicleNumberLabel.setVisible(false);
         RentStartDateLabel.setVisible(false);
         RentEndDateLabel.setVisible(false);*/
        RentalPane.setVisible(false);
    }

    @FXML
    private void NextButtonAction(ActionEvent event) throws IOException {
        System.out.println("Button pressed");
        ReturnNavigator.returnRent = searchRent;
        ReturnNavigator.returnReservation = rentReservation;
        ReturnNavigator.returnCustomer = rentCustomer;
        ReturnNavigator.returnVehicle = rentedVehicle;
        ReturnNavigator.loadVista(ReturnNavigator.ReturnInformationPage);
    }

    public void FadeTransitionMethod(Node CurrentNode) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), CurrentNode);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
