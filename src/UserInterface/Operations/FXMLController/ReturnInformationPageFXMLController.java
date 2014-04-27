/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.Operations.FXMLController;

import ControlObjects.PaymentCtrl;
import ControlObjects.StaffCtrl;
import UserInterface.Login.FXMLController.ClerkMainPageNavigator;
import entity.Return;
import entity.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReturnInformationPageFXMLController implements Initializable {

    @FXML
    private ToggleGroup FuelTG;
    @FXML
    private ToggleGroup DamagedTG;
    @FXML
    private Label DamagedLabel;
    @FXML
    private Label MissingFuelLabel;
    @FXML
    private TextField MissingFuelTF;
    @FXML
    private TextField DamagedTF;
    @FXML
    private RadioButton FuelFull;
    @FXML
    private RadioButton FuelNotFull;
    @FXML
    private RadioButton DamagedYes;
    @FXML
    private RadioButton DamagedNo;
    @FXML
    private RadioButton RoadStarYes;
    @FXML
    private ToggleGroup RoadStarTG;
    @FXML
    private RadioButton RoadStarNo;
    @FXML
    private TextField OdometerReadingTF;
    @FXML
    private Label RedeemPointsLabel;
    @FXML
    private RadioButton RedeemPointsYesRB;
    @FXML
    private ToggleGroup RedeemPointsTG;
    @FXML
    private RadioButton RedeemPointsNoRB;
    @FXML
    private TextField RedeemPointsTF;
    @FXML
    private TextField ReturnDateTF;
    @FXML
    private Label AvailablePointsLable;
    @FXML
    private ComboBox PaymentModeCB;
    @FXML
    private TextField ContractNumberTF;

    /* Variables to store the Values */
    private int missingFuel;
    private int DamagedCost;
    private int odometer;
    private boolean roadStar;
    private boolean redeemPointsFlag;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MissingFuelTF.setDisable(true);
        DamagedTF.setDisable(true);
        if (ReturnNavigator.returnCustomer.getIsClubMember()) {
            AvailablePointsLable.setDisable(false);
            RedeemPointsTF.setDisable(false);
            RedeemPointsLabel.setDisable(false);
            RedeemPointsYesRB.setDisable(false);
            RedeemPointsNoRB.setDisable(false);
            RedeemPointsTF.setText(Integer.toString(ReturnNavigator.returnCustomer.getPoint()));
            redeemPointsFlag = true;
        } else {
            redeemPointsFlag = false;
        }
        ReturnDateTF.setText((new Date()).toString());
        ContractNumberTF.setText(Integer.toString(ReturnNavigator.returnRent.getContractNo()));
    }

    @FXML
    public void FullFuelRadioAction(ActionEvent event) {
        MissingFuelTF.setDisable(true);
    }

    @FXML
    public void NotFullFuelRadioAction(ActionEvent event) {
        MissingFuelTF.setDisable(false);
    }

    @FXML
    public void DamagedRadioAction(ActionEvent event) {
        DamagedTF.setDisable(false);
    }

    @FXML
    public void NoDamagedRadioAction(ActionEvent event) {
        DamagedLabel.setOpacity(.5);
        DamagedTF.setDisable(true);
    }

    @FXML
    public void RoadStarNoCBAction(ActionEvent event) {
        roadStar = false;
    }

    @FXML
    public void RoadStarYesCBAction(ActionEvent event) {
        roadStar = true;
    }

    @FXML
    public void RedeemPointsYesRBAction(ActionEvent event) {
        redeemPointsFlag = true;
    }

    public void RedeemPointsNoRB(ActionEvent event) {
        redeemPointsFlag = false;
    }

    @FXML
    private void PaymentButtonAction(ActionEvent event) throws IOException {
        if(PaymentModeCB.valueProperty().isNotNull().getValue())
        {
        Return returninfo = new Return();
        if (FuelNotFull.isSelected() && !MissingFuelTF.getText().equals("")) {
            returninfo.setFuelLevel(Integer.parseInt(MissingFuelTF.getText()));
        } else if (FuelFull.isSelected()) {
            returninfo.setFuelLevel(0);
        } else {

            System.out.println("Please Fill in the missing fuel Details");
        }

        if (DamagedYes.isSelected() && !DamagedTF.getText().equals("")) {
            String DamagedCost = DamagedTF.getText();
            returninfo.setDamageCost(Integer.parseInt(DamagedTF.getText()));
        } else if (DamagedNo.isSelected()) {
            returninfo.setDamageCost(0);
        } else {
            System.out.println("Please Fill in the Damaged Cost Details");
        }

        if (roadStar) {
            System.out.println("Please put Road star into returninfo here");
        } else {
            System.out.println("Road Star Not selected");
        }

        if (OdometerReadingTF.getText().equals("")) {
            odometer = 0;
        } else {
            odometer = Integer.parseInt(OdometerReadingTF.getText());
            returninfo.setOdometer(odometer);
        }
        returninfo.setPaymentId(0);
        returninfo.setContractNo(ReturnNavigator.returnRent.getContractNo());

        StaffCtrl staffCtrl = new StaffCtrl();
        Staff staff = staffCtrl.getStaffByUsername(ClerkMainPageNavigator.CurrentUserName);
        returninfo.setStaffId(staff.getStaffId());
        returninfo.setReturnTime(new Date());
        PaymentCtrl newPaymentCtrl = new PaymentCtrl(ReturnNavigator.returnCustomer.getCustomerId(), "Rent Payment");
        newPaymentCtrl.addForReturn(returninfo,redeemPointsFlag, roadStar);
        
        
        String points = ReturnNavigator.newPaymentCtrl.getTotalAmountText();
        System.out.println(points);
        ReturnNavigator.Currentreturn = returninfo;
        ReturnNavigator.ActualCost = points;
        ReturnNavigator.PaymentMode = PaymentModeCB.valueProperty().getValue().toString();
        ReturnNavigator.loadVista(ReturnNavigator.RentPaymentPage);
        }else
        {
            System.out.println("Please select the Payment mode");
        }
    }

}
