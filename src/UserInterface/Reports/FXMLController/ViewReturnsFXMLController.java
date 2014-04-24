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
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewReturnsFXMLController implements Initializable {
   
    @FXML
    private TableView ReturnsTable;
    @FXML
    private TableColumn ContractNo;
    @FXML
    private TableColumn VehicleNo;
    @FXML
    private TableColumn VehicleType;
    @FXML
    private TableColumn Price;
    @FXML
    private TableColumn ReturnDate;
    @FXML
    private TableColumn ReturnTime;
    @FXML
    private TableColumn Odometer;
    @FXML
    private TableColumn FuelLevel;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private Label DateLabel;
    @FXML
    private Label ToLabel;
    @FXML
    private Label FromLabel;
    @FXML
    private Button SearchButton;
    @FXML
    private CheckBox EmailCHB;
    @FXML
    private DatePicker ToDateDP;
    @FXML
    private DatePicker FromDateDP;
    
    String fromDate;
    String toDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }

    @FXML
    private void ViewReturnsAction(ActionEvent event) {
         if (ValidateInput()) {
              toDate=(ToDateDP.getValue().toString());
              System.out.println(toDate);
              fromDate=(FromDateDP.getValue().toString());
              System.out.println(fromDate);
                        
                        
        }  else {
            System.out.println("Please enter the Date");
            DialogFX dialog = new DialogFX(DialogFX.Type.ERROR);
            dialog.setTitleText("Error");
            dialog.setMessage("Please enter the Date");
            dialog.showDialog();
        }
    }
    
     public boolean ValidateInput() {
        if (FromDateDP.valueProperty().isNotNull().getValue()
                && ToDateDP.valueProperty().isNotNull().getValue())
        {
            return true;
        } else {
            return false;
        }
    }
}
