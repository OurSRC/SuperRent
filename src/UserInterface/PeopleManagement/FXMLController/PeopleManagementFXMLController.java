/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.PeopleManagement.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class PeopleManagementFXMLController implements Initializable {
    @FXML
    private StackPane MainPPLManagementStackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            PPLManagementNavigator.setMainController(this);
            PPLManagementNavigator.loadVista(PPLManagementNavigator.UserSearch);
        } catch (IOException ex) {
            Logger.getLogger(PeopleManagementFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    @FXML
    private void EditUserButtonAction(ActionEvent event) throws IOException
    {
        PPLManagementNavigator.setMainController(this);
        PPLManagementNavigator.loadVista(PPLManagementNavigator.UserSearch);
    }

    @FXML
    private void AddUserButtonAction(ActionEvent event) throws IOException
    {
        PPLManagementNavigator.setMainController(this);
        PPLManagementNavigator.loadVista(PPLManagementNavigator.AddAUser);
        
    }
    
    public void FadeTransitionMethod(Node CurrentNode)
    {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(500), CurrentNode);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
    
    public void setStackPane(Node node)
    {
        MainPPLManagementStackPane.getChildren().setAll(node);
        FadeTransitionMethod(node);
    }
    
    public void ClearStackPane()
    {
        MainPPLManagementStackPane.getChildren().clear();
    }

   
}
