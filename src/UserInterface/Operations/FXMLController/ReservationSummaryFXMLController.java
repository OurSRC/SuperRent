/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReservationSummaryFXMLController implements Initializable {
    @FXML
    private Font x1;
    public CheckBox ProceedToRent;

    
    public void ConfirmButtonAction(ActionEvent event) throws IOException
    {
        if(ReservationNavigator.rentFlag)
        {
         //  ResReservationNavigatoradVista(ReservReservationNavigatortSpecificVehicle);   
        }
        //
    }
    
    
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
