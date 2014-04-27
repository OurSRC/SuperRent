/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.PaymentCtrl;
import SystemOperations.DateClass;
import SystemOperations.ValidateFields;
import entity.Payment;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class PaymentPageFXMLController implements Initializable {

    @FXML
    private Label CreditCardNumberLabel;
    @FXML
    private Label ExpiryDateLabel;
    @FXML
    private Label CreditCardNameLabel;
    @FXML
    private TextField CreditCardNumberTF;
    @FXML
    private DatePicker ExpiryDateTF;
    @FXML
    private TextField ExpiryDateYearTF;
    @FXML
    private TextField ExpiryDateMonthTF;
    @FXML
    private TextField CreditCardNameTF;
    @FXML
    private TextField PaymentModeTF;
    @FXML
    private TextField ContractNumberTF;
    @FXML
    private TextField VehiclePlateTF;
    @FXML
    private TextField AmountTF;

    private boolean creditCardPay;

    /* Variables to store the Values */
    private Date ExpiryDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        PaymentModeTF.setText(ReturnNavigator.PaymentMode);
        AmountTF.setText(ReturnNavigator.ActualCost);
        ContractNumberTF.setText(Integer.toString(ReturnNavigator.returnRent.getContractNo()));
        VehiclePlateTF.setText(ReturnNavigator.returnVehicle.getPlateNo());
        System.out.println(ReturnNavigator.PaymentMode);
        if (ReturnNavigator.PaymentMode.equals("Credit Card")) {
            CreditCardNumberLabel.setDisable(false);
            ExpiryDateLabel.setDisable(false);
            CreditCardNameLabel.setDisable(false);
            CreditCardNumberTF.setDisable(false);
            CreditCardNameTF.setDisable(false);
            ExpiryDateTF.setDisable(false);
            creditCardPay = true;
        }
        
        ExpiryDateYearTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(4));
        ExpiryDateMonthTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(2));
        
        
    }

    @FXML
    private void ConfirmPaymentButtonAction(ActionEvent event) throws ParseException {
        if (ValidateMandatory()) {
            if (creditCardPay) {
                ReturnNavigator.newPaymentCtrl = new PaymentCtrl(ReturnNavigator.returnCustomer.getCustomerId(), "Rent Payment", CreditCardNumberTF.getText(), ExpiryDate, CreditCardNameTF.getText());
                Payment p = ReturnNavigator.newPaymentCtrl.proceed();
            }
        } else {
            System.out.println("Please Enter all the Mandatory Fields");
        }
    }

    public boolean ValidateMandatory() throws ParseException {
        if (creditCardPay) {
            if (!CreditCardNumberTF.getText().equals("") && !CreditCardNameTF.getText().equals("") && !ExpiryDateMonthTF.getText().equals("") && !ExpiryDateYearTF.getText().equals("")) {
                if (ValidateFields.CheckIntegerNumbersOnly(ExpiryDateMonthTF.getText()) && ValidateFields.CheckIntegerNumbersOnly(ExpiryDateYearTF.getText()) && Integer.parseInt(ExpiryDateYearTF.getText()) >= ((new Date()).getYear() + 1900) && Integer.parseInt(ExpiryDateMonthTF.getText()) <= 12) {
                    ExpiryDate = DateClass.getDateObjectFromString(ExpiryDateYearTF.getText() + "/" + ExpiryDateMonthTF.getText() + "/01");
                    return true;
                } else {
                    System.out.println("Invalid Dates Entered");
                    return false;
                }
            } else {
                System.out.println("Please enter all Mandatory Values");
                return false;
            }
        } else {
            System.out.println("Paying By Cash");
            return true;
        }
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
