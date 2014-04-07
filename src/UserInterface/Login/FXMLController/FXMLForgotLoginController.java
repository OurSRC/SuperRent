package UserInterface.Login.FXMLController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
 
/**
* Controller class for the second vista.
*/
public class FXMLForgotLoginController {
 
/**
* Event handler fired when the user requests a previous vista.
*
* @param event the event that triggered the handler.
*/
@FXML
void previousPane(ActionEvent event) {
LoginNavigator.loadVista(LoginNavigator.VISTA_1);
}
 
}