package UserInterface.Login.FXMLController;

import Account.Customer;
import Account.CustomerCtrl;
import Account.LoginCtrl;
import Account.User;
import SystemOperations.ErrorMsg;
import SystemOperations.ValidateFields;
import UserInterface.Operations.FXMLController.ReservationNavigator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller class for the first vista.
 */
public class FXMLLoginController implements Initializable{

    @FXML
    private TextField UserNameTF;
    @FXML
    private PasswordField PasswordTF;
    @FXML
    private Button Login;
    @FXML
    private Label MissingDetailsMessage;
    @FXML
    private Label InvalidMessage;

    /**
     * Event handler fired when the user requests a new vista.
     *     
* @param event the event that triggered the handler.
     */
    @FXML
    void nextPane(ActionEvent event) {
        LoginNavigator.loadVista(LoginNavigator.VISTA_2);
    }

    @FXML
    public void LoginButtonAction(ActionEvent event) throws IOException {
        CustomerNavigator.CurrentUserName = "";
        ClerkMainPageNavigator.CurrentUserName = "";
        ClerkMainPageNavigator.staff=false;
        System.out.println("Hello i am here");
        LoginCtrl NewLogin = new LoginCtrl();

        String UserName = UserNameTF.getText();
        String Password = PasswordTF.getText();
        //User NewUser= new User();
        User.TYPE type = NewLogin.loginCheck(UserName, Password);
        System.out.println(type);
        switch (type) {
            case STAFF:
                /* Opening the Staff Form and Passing the UserName to the next screen */
                Stage Clerkstage = new Stage();
                FXMLLoader ClerkLoader = new FXMLLoader(getClass().getResource("/UserInterface/Login/FXML/ClerkMainPageFXML.fxml"));
                Pane ForgotPane = (Pane) ClerkLoader.load();
                FXMLClerkMainPageController Clerkcontroller = (FXMLClerkMainPageController) ClerkLoader.getController();
            //Clerkcontroller.setCurrentUser(Test1);
                //controller.setUserName(Username);
                Clerkcontroller.InitializeScreen(UserName);
                Scene Clerkscene = new Scene(ForgotPane);
                Clerkstage.setScene(Clerkscene);
                Clerkstage.setWidth(840);
                Clerkstage.setHeight(570);
                Clerkstage.setTitle(UserName.toUpperCase());
                Clerkstage.resizableProperty().setValue(Boolean.FALSE);
                ((Node) (event.getSource())).getScene().getWindow().hide();
                Clerkstage.show();
                break;
            case CUSTOMER:
                /* Opening the Customer Form and Passing the UserName to the next screen */
                CustomerCtrl newCustomerCtrl = new CustomerCtrl();
                CustomerNavigator.CurrentUserName = UserName;
                CustomerNavigator.customerFlag=true;
                System.out.println("Please open the Customer Screen here");
                Stage CustomerStage = new Stage();
                FXMLLoader CustomerLoader = new FXMLLoader(getClass().getResource("/UserInterface/Login/FXML/CustomerMainPageFXML.fxml"));
                Pane CustomerPane = (Pane) CustomerLoader.load();
                FXMLCustomerMainPageController CustomerController = (FXMLCustomerMainPageController) CustomerLoader.getController();
                Scene CustomerScene = new Scene(CustomerPane);
                CustomerStage.setScene(CustomerScene);
                ((Node) (event.getSource())).getScene().getWindow().hide();
                CustomerStage.show();
                break;
            case ERROR:
                /* Display Invalid Login Error Message Here */
                InvalidMessage.setText(ErrorMsg.translateError(ErrorMsg.getLastError()));
                InvalidMessage.setVisible(true);
                break;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        UserNameTF.lengthProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if (newValue.intValue() > oldValue.intValue()) {
                    char ch = UserNameTF.getText().charAt(oldValue.intValue());
                    System.out.println("Length:" + oldValue + "  " + newValue + " " + ch);

                    //Check if the new character is the number or other's
                    if (!(ch >= '0' && ch <= '9') && !ValidateFields.CheckLettersOnly(String.valueOf(ch))) {

                        //if it's not number then just setText to previous one
                        UserNameTF.setText(UserNameTF.getText().substring(0, UserNameTF.getText().length() - 1));
                    }
                }
            }

           

        }); 
    }
    
    
}
