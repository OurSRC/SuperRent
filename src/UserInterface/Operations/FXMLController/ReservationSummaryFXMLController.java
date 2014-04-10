/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import SystemOperations.DialogFX;
import SystemOperations.DialogFX.Type;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
//import javafx.scene.control.Dialogs;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReservationSummaryFXMLController implements Initializable {
    @FXML
    private Font x1;
    public CheckBox ProceedToRent;
   
  

    
    @FXML
    public void ConfirmButtonAction(ActionEvent event) throws IOException
    {
        DialogFX dialog = new DialogFX(Type.ACCEPT);
        dialog.setTitleText("Reservation Successfull");
        dialog.setMessage("Your Reservation Number is " + "12345" + ".");
        dialog.showDialog();
        //
    }
    
    
    @FXML
    public void AbortButtonAction(ActionEvent event) throws IOException
    {
        
       // ReserveAnReservationNavigatorta();
        // Clear the Reservation Object in the Navigator before this
      // ReserveAndReReservationNavigatoreserveAndRentNReservationNavigator    }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
    }    
    
    public void ProceedToRentAction(ActionEvent event)
    {
        //ReserveAndRentNavigatReservationNavigatornt.isSelected();
    }

   
    
    
}
