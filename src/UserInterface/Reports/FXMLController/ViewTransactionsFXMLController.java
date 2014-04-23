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
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewTransactionsFXMLController implements Initializable {

    @FXML
    private TableView TransactionsTable;
    @FXML
    private TableColumn PaymentID;
    @FXML
    private TableColumn ContractID;
    @FXML
    private TableColumn Amount;
    @FXML
    private TableColumn Method;
    @FXML
    private TableColumn Time;
    @FXML
    private TableColumn CardNo;
    @FXML
    private TableColumn CustomerID;
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
    private DatePicker FromDateDP;
    @FXML
    private DatePicker ToDateDP;

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
    private void ViewTransactionsAction(ActionEvent event) {
    }
    
}
