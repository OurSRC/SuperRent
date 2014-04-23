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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewVehiclesByYearFXMLController implements Initializable {

    @FXML
    private TableView VehiclesListTable;
    @FXML
    private TableColumn VehicleNo;
    @FXML
    private TableColumn VehicleType;
    @FXML
    private TableColumn Model;
    @FXML
    private TableColumn Year;
    @FXML
    private TableColumn Odometer;
    @FXML
    private TableColumn RateperDay;
    @FXML
    private TableColumn RateperWeek;
    @FXML
    private TableColumn Status;
    @FXML
    private Button SearchButton;
    @FXML
    private Label YearsLabel;
    @FXML
    private TextField YearsTF;
    @FXML
    private Button PrintPDFButton;
    @FXML
    private CheckBox EmailCHB;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ViewVehiclesListAction(ActionEvent event) {
    }

    @FXML
    private void PrintPDFAction(ActionEvent event) {
    }
    
}
