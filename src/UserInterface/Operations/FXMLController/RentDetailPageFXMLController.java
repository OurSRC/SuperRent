/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.CreditCardCtrl;
import ControlObjects.FinanceCtrl;
import ControlObjects.RentCtrl;
import ControlObjects.ReserveCtrl;
import ControlObjects.StaffCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import SystemOperations.ValidateFields;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import entity.Rent;
import entity.ReservationInfo;
import entity.Staff;
import finance.Price;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class RentDetailPageFXMLController implements Initializable {

    @FXML
    private CheckBox LicenseVerifiedCB;
    @FXML
    private TextField CreditCardNumberTF;
    @FXML
    private TextField CardHolderNameTF;
    @FXML
    private TextField ExpiryDateMonth;
    @FXML
    private TextField ExpiryDateYear;
    @FXML
    private ComboBox<?> CardTypeCB;
    @FXML
    private Button GenerateAgreementButton;
    @FXML
    private TextField ActualCostTF;
    @FXML
    private TextField VehicleModelTF;
    @FXML
    private TextField ManufatureDateTF;
    @FXML
    private TextField OdometerReadingTF;
    @FXML
    private TextField CardValidatedTF;
    @FXML
    private Button ValidateButton;
    @FXML
    private TextField VehiclePlateNoTF;

    /* Variables to store the variables */
    private int oldOdometerReading;
    private int newOdometerReading;
    private boolean validateFlag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VehiclePlateNoTF.setText(RentNavigator.RentVehicle.getPlateNo());
        VehicleModelTF.setText(RentNavigator.RentVehicle.getClassName());
        ManufatureDateTF.setText(RentNavigator.RentVehicle.getManufactureDate().toString());
        oldOdometerReading = RentNavigator.RentVehicle.getOdometer();
        OdometerReadingTF.setText(Integer.toString(oldOdometerReading));
        CreditCardNumberTF.setPrefColumnCount(16);
        FinanceCtrl newFinanceCtrl = new FinanceCtrl();
        int sample = newFinanceCtrl.estimateReservationCost(RentNavigator.selectedReservation);
        ActualCostTF.setText(Price.toText(sample));
        //ActualCostTF.setText(RentNavigator.selectedReservation.getPrice());
        validateFlag = false;
        ExpiryDateYear.addEventFilter(KeyEvent.KEY_TYPED, maxLength(4));
        ExpiryDateMonth.addEventFilter(KeyEvent.KEY_TYPED, maxLength(2));
    }

    @FXML
    private void GenerateAgreementButtonAction(ActionEvent event) throws IOException {
        if (ValidateMandatory()) {
            if (ValidateOdometerReading()) {
                System.out.println("I am inside the validation loop");
                RentCtrl newRentCtrl = new RentCtrl();
                Rent newRent = new Rent();
                newRent.setReservationInfold(RentNavigator.selectedReservation.getReservationInfoId());
                newRent.setOdometer(newOdometerReading);
                newRent.setVehicleNo(RentNavigator.RentVehicle.getVehicleNo());
                newRent.setFuelLevel(1);
                newRent.setCreditCardNo(CreditCardNumberTF.getText());
                StaffCtrl staffCtrl = new StaffCtrl();
                Staff staff = staffCtrl.getStaffByUsername(ClerkMainPageNavigator.CurrentUserName);
                newRent.setStaffId(staff.getStaffId());
                newRent.setTime(new Date());
                Rent createdRent = newRentCtrl.createRent(newRent);
                if (createdRent != null) {
                    ReserveCtrl newReserveCtrl = new ReserveCtrl();
                    RentNavigator.selectedReservation.setReservationStatus(ReservationInfo.STATUS.RENTED);
                    newReserveCtrl.updateReserve(RentNavigator.selectedReservation);
                    DialogFX dialog = new DialogFX(Type.INFO);
                    dialog.setTitleText("Success");
                    dialog.setMessage(" Rent Agreement Created . Agreement # : " + Integer.toString(createdRent.getContractNo()));
                    dialog.showDialog();
                    RentNavigator.loadVista(RentNavigator.ReserveSearchPage);
                } else {
                    System.out.println("Rent Creation Failed");
                }

            } else {
                System.out.println("Invalid Odometer Reading");
            }
        }
    }

    @FXML
    private void ValidateButtonAction(ActionEvent event) throws ParseException {
        if (!CreditCardNumberTF.getText().equals("") && !CardHolderNameTF.getText().equals("") && !ExpiryDateMonth.getText().equals("") && !ExpiryDateYear.getText().equals("")) {
            Date currentDate = new Date();
            if (ValidateFields.CheckIntegerNumbersOnly(ExpiryDateMonth.getText()) && ValidateFields.CheckIntegerNumbersOnly(ExpiryDateYear.getText()) && Integer.parseInt(ExpiryDateYear.getText()) >= ((new Date()).getYear() + 1900) && Integer.parseInt(ExpiryDateMonth.getText()) <= 12) {
                Date ExpiryDate = DateClass.getDateObjectFromString(ExpiryDateYear.getText() + "/" + ExpiryDateMonth.getText() + "/01");
                CreditCardCtrl.create(CreditCardNumberTF.getText(), ExpiryDate, CardHolderNameTF.getText());
                CardValidatedTF.setText("Yes");
                validateFlag = true;
            } else {
                System.out.println("Please Enter a valid date");
            }
        } else {
            System.out.println("Please Enter all the details");
        }
    }

    @FXML
    private void ChangeStatus(KeyEvent event) {
        CardValidatedTF.setText("No");
        validateFlag = false;
    }

    private boolean ValidateMandatory() {
        if (!validateFlag) {
            System.out.println("Please Validate Credit Card");
            return false;
        } else {
            if (!OdometerReadingTF.getText().equals("") && !CardHolderNameTF.getText().equals("") && !ExpiryDateMonth.getText().equals("") && !ExpiryDateYear.getText().equals(null) && !CreditCardNumberTF.getText().equals(null) && LicenseVerifiedCB.isSelected()) {
                System.out.println("All Valid Records");
                return true;

            } else {
                System.out.println("Please enter all the required fields");
                return false;
            }
        }
    }

    private boolean ValidateOdometerReading() {
        newOdometerReading = Integer.parseInt(OdometerReadingTF.getText());
        boolean validateOdometer = false;
        if (oldOdometerReading <= newOdometerReading) {
            System.out.println("I am true");
            validateOdometer = true;
        } else {
            validateOdometer = false;
        }
        return validateOdometer;
    }

    public static EventHandler<KeyEvent> maxLength(final Integer i) {
        return new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {

                TextField tx = (TextField) arg0.getSource();
                if (tx.getText().length() >= i) {
                    arg0.consume();
                }
            }
        };

    }
}
