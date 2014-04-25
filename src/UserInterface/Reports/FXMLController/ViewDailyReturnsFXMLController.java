/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import SystemOperations.DialogFX;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewDailyReturnsFXMLController implements Initializable {

    @FXML
    private TableColumn VehicleCategoryColumn;
    @FXML
    private TableColumn VehicleTypeColumn;
    @FXML
    private TableColumn NoOfReturnsColumn;
    @FXML
    private TableColumn PaymentsColumn;
    @FXML
    private Label DateLabel;
    @FXML
    private Button SearchButton;
    @FXML
    private Label PrintDateLabel;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private Label TotalLabel;
    @FXML
    private Label TotalNoOfReturnsLabel ;
    @FXML
     private Label TotalPaymentsLabel ;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    public DatePicker SearchDateDP;
    String DateValue;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SearchDailyReturnsAction(ActionEvent event) {
        if (ValidateInput()) {
              DateValue=(SearchDateDP.getValue().toString());
              System.out.println(DateValue);
                        PrintDateLabel.setText(DateValue);
                        
        }  else {
            System.out.println("Please enter the Date");
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter the Date");
            dialog.showDialog();
        }
    }

    
    public boolean ValidateInput() {
        if (SearchDateDP.valueProperty().isNotNull().getValue())
        {
            return true;
        } else {
            return false;
        }
    }
    
    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }

    @FXML
    private void SearchDateAction(ActionEvent event) {
    }
    
}
