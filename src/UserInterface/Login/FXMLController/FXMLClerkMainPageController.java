package UserInterface.Login.FXMLController;

import ControlObjects.StaffCtrl;
import UserInterface.MainController;
import entity.Staff;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Vyas
 */
public class FXMLClerkMainPageController implements Initializable {

    @FXML
    public StackPane ClerkMainStackPane;
    public Label UserName;
    public MenuItem Logout;
    public Label UserType;
    public Label LastLoginLabel;
    public MenuButton UserActionMenuButton;
    public Label UserNameLabel;
    
    public Button UserManagementButton;
    Staff NewStaff;
    public String username;
    
    
    // Buttons to Enable and Disable based on the User
    public Button HomeButton;
    public Button OperationsButton;
    public Button CustomerButton;
    public Button ActivityButton;
    public Button ReportsButton;
    public Button VehicleButton;
    public Button UserButton;


    @FXML
    public void OperationsButtonAction(ActionEvent event) throws IOException {
       ClerkMainPageNavigator.loadVista(ClerkMainPageNavigator.OPERATIONS);

    }
    
    @FXML
    public void ReportsAction(ActionEvent event) throws IOException
    {
        ClerkMainPageNavigator.loadVista(ClerkMainPageNavigator.REPORTS);
    }
    
   
    @FXML
    public void HomeButtonAction(ActionEvent event) throws IOException
    {
        ClerkMainPageNavigator.loadVista(ClerkMainPageNavigator.HOME_PAGE);
    }




    @FXML
    public void LogoutAction(ActionEvent event) {
        
        System.out.println("Inside the logout action");
        final Stage dialog = new Stage(StageStyle.TRANSPARENT);
        final Stage stage = (Stage) UserActionMenuButton.getScene().getWindow();

        dialog.initModality(Modality.WINDOW_MODAL);
        dialog.initOwner(stage);

        dialog.setScene(new Scene(HBoxBuilder.create().styleClass("modal-dialog").children(
                LabelBuilder.create().text("Would you like to logout?").build(),
                ButtonBuilder.create().text("Yes").defaultButton(true).onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            // take action and close the dialog.
                            stage.close();
                            Stage MainStage = new Stage(StageStyle.TRANSPARENT);      
                            FXMLLoader loader = new FXMLLoader();
                            Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(LoginNavigator.MAIN));
                            Scene scene = new Scene(mainPane);
                            MainStage.setScene(scene);
                            MainController mainController = loader.getController();
                            mainController.SetStage(MainStage);
                            LoginNavigator.setMainController(mainController);
                            LoginNavigator.loadVista(LoginNavigator.VISTA_1);
                            MainStage.show();
                            MainStage.setResizable(false);
                            MainStage.setWidth(605);
                            MainStage.setHeight(445);
                            MainStage.show();
                            stage.getScene().getRoot().setEffect(null);
                            dialog.close();
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLClerkMainPageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }).build(),
                ButtonBuilder.create().text("No").cancelButton(true).onAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        stage.setOpacity(1);

                        stage.getScene().getRoot().setEffect(null);
                        dialog.close();
                    }
                }).build()
        ).build(), null
        )
        );
        stage.setOpacity(.75);
        dialog.show();
    }
    
    
    @FXML
    public void UpdateInformationAction(ActionEvent event) throws IOException
    {
        
    }

    public void setCurrentUserDetails()
    {
        
    }
    
    public void InitializeScreen(String userName)
    {
                ClerkMainPageNavigator.loadVista(ClerkMainPageNavigator.HOME_PAGE);

        UserNameLabel.setText(userName);
        username= userName;
        ClerkMainPageNavigator.CurrentUserName=userName;
        ClerkMainPageNavigator.staff=true;
        StaffCtrl StaffControl = new StaffCtrl();
        NewStaff = StaffControl.getStaffByUsername(username);
        System.out.println(NewStaff.getStaffType() + "" + NewStaff.getStaffId());
        
        switch (NewStaff.getStaffType())
        {
            case CLERK:
                UserButton.setVisible(false);
                VehicleButton.setVisible(false);
                break;
                
            case MANAGER:
                UserButton.setVisible(false);
                break;
                
            case ADMIN:
                /* Should be able to see all the menu's*/
                break;
            
            default :
                System.out.println("Your User ID is corrupted . Please contact the System Admin");
        }
    }
    
    public void InitializeScreenforUser()
    {
        StaffCtrl StaffControl = new StaffCtrl();
        NewStaff = StaffControl.getStaffByUsername(username);
        ClerkMainPageNavigator.CurrentUserName = username;
        System.out.println(NewStaff.getStaffType() + "" + NewStaff.getStaffId());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Hello i am here inside the initialize");
     //   FXMLLoader loader = new FXMLLoader();

      //  Pane mainPane;
      //  FXMLClerkMainPageController mainController = loader.getController();
        ClerkMainPageNavigator.setMainController(this);
        //ClerkMainPageNavigator.loadVista(ClerkMainPageNavigator.HOME_PAGE);
    }
    
    public void VehicleButtonAction(ActionEvent event)
    {
               ClerkMainPageNavigator.loadVista(ClerkMainPageNavigator.FLEET_MANAGEMENT);        
    }
    
    public void UserButtonAction(ActionEvent event)
    {
        ClerkMainPageNavigator.loadVista(ClerkMainPageNavigator.USER_MANAGEMENT);        
    }
    
    public void setStackPane(Node node) {
                ClerkMainStackPane.getChildren().setAll(node);

    }


}
