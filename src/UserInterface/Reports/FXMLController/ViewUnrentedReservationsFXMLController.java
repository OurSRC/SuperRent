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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ViewUnrentedReservationsFXMLController implements Initializable {

    @FXML
    private TableView UnrentedTable;
    @FXML
    private TableColumn Reservationno;
    @FXML
    private TableColumn VehicleType;
    @FXML
    private TableColumn Price;
    @FXML
    private TableColumn PickupDate;
    @FXML
    private TableColumn ReturnDate;
    @FXML
    private TableColumn CusID;
    @FXML
    private TableColumn CusPhone;
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
    private void PrintPDFAction(ActionEvent event) {
    }
    
}
