/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Reports.FXMLController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Ashanthi Perera
 */
public class ReportsMainPageFXMLController implements Initializable {
    @FXML
    private Button ViewDailyRentalsButton;
    @FXML
    private Button ViewAvailableVehiclesButton;
    @FXML
    private Button ViewDailyReturnsButton;
    @FXML
    private Font x1;
    @FXML
    private Button ViewVehiclesForSaleButton;
    @FXML
    private Button ViewVehiclesNotReturnedButton;
    @FXML
    private Button ViewUnrentedReservationsButton;
    @FXML
    private StackPane ReportsManagementStackPane;
    @FXML
    private Button ViewTransactionsButton;
    @FXML
    private Button ViewRentedVehiclesButton;
    @FXML
    private Button ViewReturnedVehiclesButton;
    @FXML
    private Button ViewVehiclesListButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            ReportsManagementStackPane.getChildren().clear();
            FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewDailyReturnsFXML.fxml"));
            Pane ViewDailyReturnsPane  = (Pane)myLoader.load();
            ReportsManagementStackPane.getChildren().add(ViewDailyReturnsPane);
        } catch (IOException ex) {
            Logger.getLogger(ReportsMainPageFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    //Report - Daily Rentals.
    @FXML
    private void ViewDailyRentalsAction(ActionEvent event) throws IOException
    {
        
       ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewDailyRentalsFXML.fxml"));
       Pane ViewDailyRentalsPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewDailyRentalsPane);
    }
    
    //Report - Daily Returns
    @FXML
    private void ViewDailyReturnsAction(ActionEvent event) throws IOException
    {
        
        ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewDailyReturnsFXML.fxml"));
       Pane ViewDailyReturnsPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewDailyReturnsPane);
    }

   
    //Report - Available Vehicles for Rent
    @FXML
    private void ViewAvailableVehiclesAction(ActionEvent event) throws IOException
    {
      ReportsManagementStackPane.getChildren().clear();
      FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewAvailableVehiclesFXML.fxml"));
      Pane ViewAvailableVehiclesPane  = (Pane)myLoader.load();
      ReportsManagementStackPane.getChildren().add(ViewAvailableVehiclesPane);
    }

     //Report - Vehicles Not Returned - overdue
    @FXML
    private void ViewNotReturnedAction(ActionEvent event) throws IOException  
    {
       ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewVehiclesNotReturnedFXML.fxml"));
       Pane ViewNotReturnedPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewNotReturnedPane);
    }

    //Report - Vehicles Not Rented - overdue
    @FXML
    private void ViewNotRentedAction(ActionEvent event) throws IOException 
    {
       ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewUnrentedReservationsFXML.fxml"));
       Pane ViewUnrentedPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewUnrentedPane);
    }
    
    //Report - Vehicles for Sale
    @FXML
    private void ViewVehiclesforSaleAction(ActionEvent event)throws IOException {
       ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewVehiclesforSaleFXML.fxml"));
       Pane ViewVehiclesforSalePane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewVehiclesforSalePane);
    }

    

    
     //Report - List of Rentals (for Manager)
    @FXML
    private void ViewRentedAction(ActionEvent event) throws IOException
    {
      ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewRentalsFXML.fxml"));
       Pane ViewAllRentalsPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewAllRentalsPane);
    }
    
    //Report- List of Returned Cars (for Manager)
    @FXML
    private void ViewReturnedAction(ActionEvent event) throws IOException
    {
       ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewReturnsFXML.fxml"));
       Pane ViewAllReturnsPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewAllReturnsPane);
     }

        //Report - List of Vehicles by year (for Manager)
    @FXML
    private void ViewVehiclesByYearAction(ActionEvent event) throws IOException
    {
       ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewVehiclesByYearFXML.fxml"));
       Pane ViewVehiclesByYearPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewVehiclesByYearPane);
     }
    
    //Report - Transactions (for Manager)
    @FXML
    private void ViewTransactionsAction(ActionEvent event) throws IOException
    {
       ReportsManagementStackPane.getChildren().clear();
       FXMLLoader myLoader = new  FXMLLoader(getClass().getResource("/UserInterface/Reports/FXML/ViewTransactionsFXML.fxml"));
       Pane ViewTransactionsPane  = (Pane)myLoader.load();
       ReportsManagementStackPane.getChildren().add(ViewTransactionsPane);
     }



   

 
    
}
