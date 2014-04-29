package UserInterface;

import UserInterface.Login.FXMLController.LoginNavigator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

/**
 * Main application class.
 */
public class StartApplication extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Stage MainStage = new Stage();

        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(LoginNavigator.MAIN));
        MainStage.setScene(createScene(mainPane));

        MainController mainController = loader.getController();
        mainController.SetStage(MainStage);
        LoginNavigator.setMainController(mainController);
        LoginNavigator.loadVista(LoginNavigator.VISTA_1);

        MainStage.show();
        MainStage.setResizable(false);
        MainStage.setWidth(630);
        MainStage.setHeight(509);
        MainStage.show();
    }

    /**
     * Loads the main fxml layout. Sets up the vista switching VistaNavigator.
     * Loads the first vista into the fxml layout.
     *     
* @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    private Pane loadMainPane() throws IOException {
        /*FXMLLoader loader = new FXMLLoader();
 
         Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(VistaNavigator.MAIN));
 
         MainController mainController = loader.getController();
 
         VistaNavigator.setMainController(mainController);
         VistaNavigator.loadVista(VistaNavigator.VISTA_1);
 
         return mainPane;*/
        return null;
    }

    /**
     * Creates the main application scene.
     *     
* @param mainPane the main application layout.
     *     
* @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
