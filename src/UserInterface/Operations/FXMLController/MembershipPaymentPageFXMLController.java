/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.BranchCtrl;
import ControlObjects.CustomerCtrl;
import ControlObjects.FinanceCtrl;
import ControlObjects.PaymentCtrl;
import SystemOperations.DateClass;
import SystemOperations.DialogFX;
import SystemOperations.ValidateFields;
import static UserInterface.Operations.FXMLController.PaymentPageFXMLController.maxLength;
import entity.Branch;
import entity.Customer;
import entity.Payment;
import entity.PaymentItem;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class MembershipPaymentPageFXMLController implements Initializable {

    @FXML
    private Label CreditCardNumberLabel;
    @FXML
    private Label ExpiryDateLabel;
    @FXML
    private Label CreditCardNameLabel;
    @FXML
    private TextField CreditCardNumberTF;
    @FXML
    private TextField CreditCardNameTF;
    @FXML
    private TextField NumberOfYearsTF;
    @FXML
    private TextField ExpiryDateYearTF;
    @FXML
    private TextField ExpiryDateMonthTF;
    @FXML
    private Label CustomerNameLabel;
    @FXML
    private Label AmountLabel;
    private Customer customer;
    @FXML
    private Label MembershipFeeLabel;
    @FXML
    private ComboBox CardTypeCB;
    private int branchId;
    private int noYears;
    private PaymentCtrl paymentCtrl;
    @FXML
    private Label CreditCardTypeLabel;
    @FXML
    private Label slashLabel;
    private Date ExpiryDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        branchId = 1;
        BranchCtrl branchCtrl = new BranchCtrl();
        Branch branch = branchCtrl.getBranchById(branchId);
        CustomerNameLabel.setText(customer.getFirstName() + " " + customer.getLastName());
        MembershipFeeLabel.setText(String.valueOf(branch.getClubMemberFeeRate()));

        paymentCtrl = new PaymentCtrl(customer.getCustomerId(), "SuperRent Club Membership Fee");

        CreditCardNumberLabel.setDisable(true);
        CreditCardNameLabel.setDisable(true);
        ExpiryDateLabel.setDisable(true);
        CreditCardTypeLabel.setDisable(true);
        CreditCardNumberTF.setDisable(true);
        CreditCardNameTF.setDisable(true);
        ExpiryDateYearTF.setDisable(true);
        ExpiryDateMonthTF.setDisable(true);
        CardTypeCB.setDisable(true);
        slashLabel.setDisable(true);

        ExpiryDateYearTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(4));
        ExpiryDateMonthTF.addEventFilter(KeyEvent.KEY_TYPED, maxLength(2));
    }

    @FXML
    private void ConfirmPaymentButtonAction(ActionEvent event) {
        try{
        if (ValidateMandatory()) {
            paymentCtrl.useCreditCard(CreditCardNumberTF.getText(), ExpiryDate, CreditCardNameTF.getText());
            Payment p = paymentCtrl.proceed();
        }
        } catch(ParseException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void CancelButtonAction(ActionEvent event) {
    }

    public void storeCustomer(String Customerphone) {
        CustomerCtrl newCustomerCtrl = new CustomerCtrl();
        customer = newCustomerCtrl.getCustomerByPhone(Customerphone);
    }

    @FXML
    private void NumberofYearsTFAction(ActionEvent event) {
        if (ValidateFields.CheckIntegerNumbersOnly(NumberOfYearsTF.getText())) {
            noYears = Integer.parseInt(NumberOfYearsTF.getText());

            paymentCtrl.addForMembershipFee(noYears, branchId);
            int total = paymentCtrl.getTotalAmount();
            String totalAmountString = paymentCtrl.getTotalAmountText();
            AmountLabel.setText(paymentCtrl.getTotalAmountText());
            CreditCardNumberLabel.setDisable(false);
            CreditCardNameLabel.setDisable(false);
            ExpiryDateLabel.setDisable(false);
            CreditCardTypeLabel.setDisable(false);
            CreditCardNumberTF.setDisable(false);
            CreditCardNameTF.setDisable(false);
            ExpiryDateYearTF.setDisable(false);
            ExpiryDateMonthTF.setDisable(false);
            CardTypeCB.setDisable(false);
            slashLabel.setDisable(false);
        }
    }

    public boolean ValidateMandatory() throws ParseException {
        if (!CreditCardNumberTF.getText().equals("") && !CreditCardNameTF.getText().equals("") && !ExpiryDateMonthTF.getText().equals("") && !ExpiryDateYearTF.getText().equals("")) {
            if (ValidateFields.CheckIntegerNumbersOnly(ExpiryDateMonthTF.getText()) && ValidateFields.CheckIntegerNumbersOnly(ExpiryDateYearTF.getText()) && Integer.parseInt(ExpiryDateYearTF.getText()) >= ((new Date()).getYear() + 1900) && Integer.parseInt(ExpiryDateMonthTF.getText()) <= 12) {
                ExpiryDate = DateClass.getDateObjectFromString(ExpiryDateYearTF.getText() + "/" + ExpiryDateMonthTF.getText() + "/01");
                return true;
            } else {
                DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage(" Invalid Dates Entered");
                dialog.showDialog();

                return false;
            }
        } else {
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage(" Please enter all Mandatory Values");
            dialog.showDialog();
            return false;
        }
    }

}
