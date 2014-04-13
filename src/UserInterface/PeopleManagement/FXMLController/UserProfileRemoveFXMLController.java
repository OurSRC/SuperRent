/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.PeopleManagement.FXMLController;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ali
 */
public class UserProfileRemoveFXMLController implements Initializable {
    @FXML
    private Font x1;
    @FXML
    private TextField UserNameTF;
    @FXML
    private TextField FirstNameTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private Font x2;
    @FXML
    private PasswordField SetNewPasswordTF;
    @FXML
    private PasswordField ReenterNewPasswordTF;
    @FXML
    private ComboBox<?> BranchCB;
    @FXML
    private ComboBox<?> RoleCB;
    @FXML
    private TextField PhoneNumberTF;
    @FXML
    private TextField EmailTF;
    @FXML
    private Button ChangePasswordButtonAction;
    @FXML
    private ComboBox<?> StatusCB;
    @FXML
    private TextField MiddleNameTF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ConfirmButtonAction(ActionEvent event) {
    }

    @FXML
    private void AbortButtonAction(ActionEvent event) {
    }

    @FXML
    private void BranchCBAction(ActionEvent event) {
    }

    @FXML
    private void RoleCBAction(ActionEvent event) {
    }

    @FXML
    private void ChangePasswordButtonAction(ActionEvent event) {
    }

    @FXML
    private void StatusCBAction(ActionEvent event) {
    }
    
}
