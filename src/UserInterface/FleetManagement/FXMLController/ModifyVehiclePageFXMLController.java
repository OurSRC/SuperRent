/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.FleetManagement.FXMLController;

import ControlObjects.VehicleCtrl;
import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import entity.Vehicle;
import entity.VehicleClass;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ModifyVehiclePageFXMLController implements Initializable {

    @FXML
    private TextField PlateNumberTF;
    @FXML
    private TextField ManufactureDate;
    @FXML
    private TextField ModelTF;
    @FXML
    private TextField OdometerTF;
    @FXML
    private ComboBox StatusCB;
    @FXML
    private TextField VehicleClassTF;
    @FXML
    private TextField VehicleTypeTF;
    @FXML
    private Label SellingPriceLabel;
    @FXML
    private TextField SellingPriceTF;

    public VehicleClass.TYPE vehicleType;

    /* Variables to store the Screen values */
    public String plateNumber;
    public String model;
    public int odometerReading;
    public int sellingPrice;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        VehicleCtrl newVehicleCtrl = new VehicleCtrl();
        vehicleType = newVehicleCtrl.getVehicleTypeByClassName(VehicleNavigator.Modifyvehicle.getClassName());
        VehicleTypeTF.setText(vehicleType.toString());
        VehicleClassTF.setText(VehicleNavigator.Modifyvehicle.getClassName());
        PlateNumberTF.setText(VehicleNavigator.Modifyvehicle.getPlateNo());
        ManufactureDate.setText(VehicleNavigator.Modifyvehicle.getManufactureDate().toString());
        ModelTF.setText(VehicleNavigator.Modifyvehicle.getMode());
        OdometerTF.setText(Integer.toString(VehicleNavigator.Modifyvehicle.getOdometer()));
        switch (VehicleNavigator.Modifyvehicle.getStatus().toString()) {
            case "FORRENT":
                StatusCB.getSelectionModel().select(0);
                break;
            case "FORSALE":
                StatusCB.getSelectionModel().select(1);
                SellingPriceTF.setDisable(false);
                SellingPriceLabel.setDisable(false);
                SellingPriceTF.setText(Integer.toString(VehicleNavigator.Modifyvehicle.getPrice()));
                break;
        }
    }

    @FXML
    private void StatusCBAction(ActionEvent event) {
        if (StatusCB.getValue().toString().equals("Available For Sale")) {
            VehicleNavigator.Modifyvehicle.setStatus(Vehicle.STATUS.FORSALE);
            VehicleNavigator.Modifyvehicle.setSellStatus(Vehicle.SELLSTATUS.FORSALE);
            SellingPriceTF.setDisable(false);
            SellingPriceLabel.setDisable(false);
        } else {
            VehicleNavigator.Modifyvehicle.setStatus(Vehicle.STATUS.FORRENT);
            VehicleNavigator.Modifyvehicle.setSellStatus((Vehicle.SELLSTATUS) null);
            SellingPriceTF.setText(null);
            SellingPriceTF.setDisable(true);
            SellingPriceLabel.setDisable(true);
        }
    }

    @FXML
    private void UpdateVehicleButtonAction(ActionEvent event) throws IOException {
        if(ValidateMandatoryField())
        {
            VehicleNavigator.Modifyvehicle.setPlateNo(plateNumber);
            VehicleNavigator.Modifyvehicle.setMode(model);
            VehicleNavigator.Modifyvehicle.setPrice(sellingPrice);
            VehicleNavigator.Modifyvehicle.setOdometer(odometerReading);
            VehicleCtrl newVehicleCtrl = new VehicleCtrl();
            if(newVehicleCtrl.updateVehicle(VehicleNavigator.Modifyvehicle))
            {
                 DialogFX dialog = new DialogFX(Type.INFO);
                dialog.setTitleText("Success");
                dialog.setMessage("Vehicle " + plateNumber + " Successfully updated");
                dialog.showDialog();      
                VehicleNavigator.loadVista(VehicleNavigator.VEHICLEMAINPAGE);
            }
            
        }else
        {
                DialogFX dialog = new DialogFX(Type.ERROR);
                dialog.setTitleText("Error");
                dialog.setMessage(" Pleas enter the Mandatory Fields");
                dialog.showDialog();
        }

    }

    @FXML
    private void BackToSearchButtonAction(ActionEvent event) {
    }

    public boolean ValidateMandatoryField() {
        if (!PlateNumberTF.getText().equals("")
                && !ModelTF.getText().equals("")
                && !OdometerTF.getText().equals("")
                && StatusCB.getSelectionModel().getSelectedIndex() == 1
                && !SellingPriceTF.getText().equals("")) {
            plateNumber = PlateNumberTF.getText();
            model = ModelTF.getText();
            odometerReading = Integer.parseInt(OdometerTF.getText());
            sellingPrice = Integer.parseInt(SellingPriceTF.getText());
            return true;

        } else if (!PlateNumberTF.getText().equals("")
                && !ModelTF.getText().equals("")
                && !OdometerTF.getText().equals("")
                && StatusCB.getSelectionModel().getSelectedIndex() == 0) {
                plateNumber = PlateNumberTF.getText();
                model = ModelTF.getText();
                odometerReading = Integer.parseInt(OdometerTF.getText());
                sellingPrice = 0;
            return true;
        } else {
            return false;
        }
    }

}
