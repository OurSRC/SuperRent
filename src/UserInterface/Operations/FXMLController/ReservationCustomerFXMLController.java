
package UserInterface.Operations.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 *
 * @author Vyas
 */
public class ReservationCustomerFXMLController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void NextButtonAction(ActionEvent event) throws IOException
    {
        ReservationNavigator.clearVista();
        //CreateReservationNavigator.loadVista(ReservationNavigator.SelectVehicle);
                ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);

    }
    
    public void BackButtonAction(ActionEvent event) throws IOException
    {
        System.out.println("I am inside this.Please help");
        ReservationNavigator.clearVista();
        ReservationNavigator.loadVista(ReservationNavigator.ADDITIONALEQUIPMENTS);
    }
    
    
}
