/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        private Label DamagedLabel;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //DamagedLabel.setOpacity(.5);
        //MissingFuelLabel.setOpacity(.5);
        MissingFuelTF.setDisable(true);
        DamagedTF.setDisable(true);
        
    }    
    
    @FXML
    public void FullFuelRadioAction(ActionEvent event)
    {
        MissingFuelTF.setDisable(true);
    }
    
    @FXML
    public void NotFullFuelRadioAction(ActionEvent event)
    {
        MissingFuelTF.setDisable(false);
    }
    
    @FXML
    public void DamagedRadioAction(ActionEvent event)
    {
        DamagedTF.setDisable(false);
    }
    
    @FXML
    public void NoDamagedRadioAction(ActionEvent event)
    {
        DamagedLabel.setOpacity(.5);
        DamagedTF.setDisable(true);   
    }

    @FXML
    private void PaymentButtonAction(ActionEvent event) {
    }
    
}
