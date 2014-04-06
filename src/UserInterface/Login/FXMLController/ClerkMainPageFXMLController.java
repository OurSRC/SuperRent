/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Login.FXMLController;

import UserInterface.*;
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
import javafx.scene.Scene;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Vyas
 */
public class ClerkMainPageFXMLController implements Initializable {
    @FXML
    private MenuButton UserActionMenuButton;
    @FXML
    private Label UserNameLabel;
    @FXML
    private StackPane ClerkMainStackPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void HomeButtonAction(ActionEvent event) {
    }

    @FXML
    private void OperationsButtonAction(ActionEvent event) {
    }

    @FXML
    private void CustomerButtonAction(ActionEvent event) {
    }

    @FXML
    private void ReportsAction(ActionEvent event) {
    }

    @FXML
    private void LogoutAction(ActionEvent event) {
        
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
                        // take action and close the dialog.
                        stage.close();

                        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/UserInterface/Login/FXML/LoginMainPage.fxml"));
                        Stage MainStage = new Stage(StageStyle.TRANSPARENT);
                        Pane MainPagePane = null;
                        try {
                            MainPagePane = (Pane) myLoader.load();
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLClerkMainPageController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        MainController controller = (MainController) myLoader.getController();
                        try {
                            controller.SetStage(MainStage);
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLClerkMainPageController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        stage.setTitle("Login");
                        Scene myScene = new Scene(MainPagePane);
                        MainStage.setScene(myScene);

                        MainStage.setResizable(false);
                        MainStage.setWidth(605);
                        MainStage.setHeight(445);
                        MainStage.show();
                        stage.getScene().getRoot().setEffect(null);
                        dialog.close();
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
    
}
