/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ReturnMainPageFXMLController implements Initializable {
    @FXML
    private StackPane ReturnInnerPane;
    public TextField CustomerNameTF;
    public TextField CustomerPhoneTF;
    public TextField VehicleTypeTF;
    public Label CustomerNumberLabel;
    public Label  VechicleTypeLabel;
    public Label  VehicleNumberLabel;
    public Label  RentStartDateLabel;
    public Label  RentEndDateLabel;
    
    public Pane RentalPane;
    public Pane BottomPane;
    @FXML
    private Label CustomerNameLabel;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    public void SearchRentButtonAction(ActionEvent event)
    {   
    CustomerNameTF.setVisible(true);
    CustomerNameTF.setVisible(true);
    CustomerPhoneTF.setVisible(true);
    VehicleTypeTF.setVisible(true);
    CustomerNumberLabel.setVisible(true);
    VechicleTypeLabel.setVisible(true);
    VehicleNumberLabel.setVisible(true);
    RentStartDateLabel.setVisible(true);
    RentEndDateLabel.setVisible(true);
    RentalPane.setVisible(true);

       
        /*FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), RentalPane);
        FadeTransition fadeTransition1 = new FadeTransition(Duration.millis(500), BottomPane);
        fadeTransition1.setFromValue(0.0);
        fadeTransition1.setToValue(1.0);

        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        fadeTransition1.play();*/
    
        FadeTransitionMethod(RentalPane);
        FadeTransitionMethod(BottomPane);

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CustomerNameTF.setVisible(false);
        
        
   /* CustomerNameTF.setVisible(false);
    CustomerPhoneTF.setVisible(false);
    VehicleTypeTF.setVisible(false);
    CustomerNumberLabel.setVisible(false);
    VechicleTypeLabel.setVisible(false);
    VehicleNumberLabel.setVisible(false);
    RentStartDateLabel.setVisible(false);
    RentEndDateLabel.setVisible(false);*/
    RentalPane.setVisible(false);
    }  
    
    @FXML
    private void NextButtonAction(ActionEvent event) throws IOException
    {
        System.out.println("Button pressed");
        ReturnNavigator.loadVista(ReturnNavigator.ReturnInformationPage);
    }
    
    
    public void FadeTransitionMethod(Node CurrentNode)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), CurrentNode);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}
