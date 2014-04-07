package UserInterface;

import UserInterface.Login.FXMLController.LoginNavigator;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
 
/**
* Main controller class for the entire layout.
*/
public class MainController {
 
/** Holder of a switchable vista. */
@FXML
private StackPane LoginStackPane;
public Button CloseButton;
public Stage PrevStage;

/**
* Replaces the vista displayed in the vista holder with a new vista.
*
* @param node the vista node to be swapped in.
*/
public void setVista(Node node) {
LoginStackPane.getChildren().setAll(node);
}

public void LoginButtonAction(ActionEvent event)
{  
LoginNavigator.loadVista(LoginNavigator.VISTA_1);
}

public void RegisterButtonAction(ActionEvent event)
{
    LoginNavigator.loadVista(LoginNavigator.Register);
}


public void SetStage(Stage stage) throws IOException {
        this.PrevStage = stage;
}

@FXML
    private void CloseButtonAction(ActionEvent event) {
        Stage stage = (Stage) CloseButton.getScene().getWindow();
        stage.close();
    }
 

 
}