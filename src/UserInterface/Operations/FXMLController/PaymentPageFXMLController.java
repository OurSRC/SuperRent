/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.PaymentCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import SystemOperations.ValidateFields;
import entity.Payment;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
            ExpiryDateMonthTF.setDisable(false);
            ExpiryDateYearTF.setDisable(false);
            creditCardPay = true;
        }
        CreditCardNumberTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(16));
        ExpiryDateYearTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(4));
        ExpiryDateMonthTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(2));

        CreditCardNameTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = CreditCardNameTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!ValidateFields.CheckLettersOnly(String.valueOf(ch))) {

                        //if it's not number then just setText to previous one
                        CreditCardNameTF.setText(CreditCardNameTF.getText().substring(0, CreditCardNameTF.getText().length() - 1));
                    }
                }
            }

        });

        ExpiryDateYearTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = ExpiryDateYearTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        ExpiryDateYearTF.setText(ExpiryDateYearTF.getText().substring(0, ExpiryDateYearTF.getText().length() - 1));
                    }
                }
            }

        });

        ExpiryDateMonthTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = ExpiryDateMonthTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        ExpiryDateMonthTF.setText(ExpiryDateMonthTF.getText().substring(0, ExpiryDateMonthTF.getText().length() - 1));
                    }
                }
            }

        });
        
        CreditCardNumberTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = CreditCardNumberTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9')) {

                        //if it's not number then just setText to previous one
                        CreditCardNumberTF.setText(CreditCardNumberTF.getText().substring(0, CreditCardNumberTF.getText().length() - 1));
                    }
                }
            }

           

        }); 
    }

    @FXML
    private void ConfirmPaymentButtonAction(ActionEvent event) throws ParseException , IOException{
        if (ValidateMandatory()) {
            if (creditCardPay) {
                //ReturnNavigator.newPaymentCtrl = new PaymentCtrl(ReturnNavigator.returnCustomer.getCustomerId(), "Rent Payment", CreditCardNumberTF.getText(), ExpiryDate, CreditCardNameTF.getText());
                ReturnNavigator.newPaymentCtrl.useCreditCard(CreditCardNumberTF.getText(), ExpiryDate, CreditCardNameTF.getText());
                Payment p = ReturnNavigator.newPaymentCtrl.proceed();
                System.out.println("I am here -----");
                DialogFX dialog = new DialogFX(Type.INFO);
                dialog.setTitleText("Return Success");
                dialog.setMessage("Vehicle Plate Number " + ReturnNavigator.returnVehicle.getPlateNo() + " successfully Returned");
                dialog.showDialog();
                ReturnNavigator.loadVista(ReturnNavigator.ReturnMainPage);
                
            } else {
                Payment p = ReturnNavigator.newPaymentCtrl.proceed();
                DialogFX dialog = new DialogFX(Type.INFO);
                dialog.setTitleText("Return Success");
                dialog.setMessage("Vehicle Plate Number " + ReturnNavigator.returnVehicle.getPlateNo() + " successfully Returned");
                dialog.showDialog();
                ReturnNavigator.loadVista(ReturnNavigator.ReturnMainPage);
            }

        } else {
            System.out.println("Please Enter all the Mandatory Fields");
            ;
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
                    DialogFX dialog = new DialogFX(Type.ERROR);
                    dialog.setTitleText("Invalid Date Entered");
                    dialog.setMessage("Please input Valid Year and Month");
                    dialog.showDialog();
                    return false;
                }
            } else {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Details Required");
                dialog.setMessage("Please enter all Mandatory Fields");
                dialog.showDialog();
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
