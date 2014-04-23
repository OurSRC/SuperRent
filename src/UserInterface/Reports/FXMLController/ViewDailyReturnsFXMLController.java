/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

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
    private TableColumn VehicleCategory;
    @FXML
    private TableColumn VehicleType;
    @FXML
    private TableColumn NoOfReturns;
    @FXML
    private TableColumn Payments;
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
    private TextField TotalReturnsTF;
    @FXML
    private TextField TotalPaymentsTF;
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
              DateValue=(SearchDateDP.getValue().toString());
        System.out.println(DateValue);
                        PrintDateLabel.setText(DateValue);
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }

    @FXML
    private void SearchDateAction(ActionEvent event) {
    }
    
}