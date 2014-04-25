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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewDailyRentalsFXMLController implements Initializable {

    @FXML
    private TableView DailyRentalsTable;
    @FXML
    private TableColumn VehicleCategory;
    @FXML
    private TableColumn VehicleType;
    @FXML
    private TableColumn NoOfRentals;
    @FXML
    private Label PrintDateLabel;
    @FXML
    private Label DateLabel;
    @FXML
    private Button SearchButton;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private Label TotalRentalsLabel;
    @FXML
    private Label PrintTotalRentalsLabel;
    //@FXML
    //private TextField TotalRentalsTF;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    public DatePicker SearchDateDP;
    
    
    public String DateValue;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SearchDailyRentalsAction(ActionEvent event) {
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
