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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DamagedLabel.setOpacity(.5);
        MissingFuelLabel.setOpacity(.5);
        MissingFuelTF.setDisable(true);
        DamagedTF.setDisable(true);
        
    }    
    
    public void FullFuelRadioAction(ActionEvent event)
    {
        MissingFuelLabel.setOpacity(.5);
        MissingFuelTF.setDisable(true);
    }
    
    public void NotFullFuelRadioAction(ActionEvent event)
    {
        MissingFuelLabel.setOpacity(1);
        MissingFuelTF.setDisable(false);
    }
    
    public void DamagedRadioAction(ActionEvent event)
    {
        DamagedLabel.setOpacity(1);
        DamagedTF.setDisable(false);
    }
    
    public void NoDamagedRadioAction(ActionEvent event)
    {
        DamagedLabel.setOpacity(.5);
        DamagedTF.setDisable(true);   
    }

    @FXML
    private void PaymentButtonAction(ActionEvent event) {
    }
    
}
