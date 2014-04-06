package UserInterface.Operations.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Vyas
 */
public class CreateReservationFXMLController  implements Initializable{
    
        private int Reservation_Stage;
        public Button NextButton;
        public Button BackButton;
        public TextField CustomerTF;
        public StackPane ReservationChangePane;
        public Pane MainPane;
        public Pane AdditionalEquipPane;
        public Pane FirstPane;
        public Pane CustomerPane;
        public ProgressBar ReservationProgress;
        public ComboBox VehicleClassComboBox;
        public TextField VehicleClassSelectedTF;
        
    public void InitializeScreen() throws IOException
    {
        
        
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           /* try {
                System.out.println("Inside Initialize here");
                //ReservationChangePane.getChildren().clear();
                //ReservationChangePane.getChildren().clear();
                CreateReservationNavigator.SampleSharedVariable=null;
                CreateReservationNavigator.setMainController(this);
                CreateReservationNavigator.loadVista(CreateReservationNavigator.PickDate);
            } catch (IOException ex) {
                Logger.getLogger(CreateReservationFXMLController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
    }   
    
    public void setStackPane(Node node)
    {
        ReservationChangePane.getChildren().addAll(node);
    }
    
    public void ClearStackPane()
    {
        ReservationChangePane.getChildren().clear();
    }

    
    
}
