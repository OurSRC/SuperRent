/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import ControlObjects.PaymentCtrl;
import SystemOperations.DateClass;
import entity.Payment;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
        if(ReturnNavigator.PaymentMode.equals("Credit Card"))
        {
            CreditCardNumberLabel.setDisable(false);
            ExpiryDateLabel.setDisable(false);
            CreditCardNameLabel.setDisable(false);
            CreditCardNumberTF.setDisable(false);
            CreditCardNameTF.setDisable(false);
            ExpiryDateTF.setDisable(false);
            creditCardPay = true;
        }
    }    

    @FXML
    private void ConfirmPaymentButtonAction(ActionEvent event) throws ParseException {
        if(ValidateMandatory())
        {
            if(creditCardPay)
            {
            ReturnNavigator.newPaymentCtrl = new PaymentCtrl(ReturnNavigator.returnCustomer.getCustomerId(), "Rent Payment" , CreditCardNumberTF.getText() , DateClass.getDateObject(ExpiryDateTF.getValue()),CreditCardNameTF.getText());
            Payment p = ReturnNavigator.newPaymentCtrl.proceed();
            }
        }else
        {
            System.out.println("Please Enter all the Mandatory Fields");
        }
    }
    
    public boolean ValidateMandatory()
    {
        if(creditCardPay)
        {
           if(!CreditCardNumberTF.getText().equals("") && !CreditCardNameTF.getText().equals("") && ExpiryDateTF.valueProperty().isNotNull().getValue()) 
           {
               return true;
           }else
           {
               return false;
           }
        }else
        {
            return true;
        }
    }
    
}
