package UserInterface.Operations.FXMLController;

import ControlObjects.CustomerCtrl;
import entity.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 *
 * @author Vyas
 */
public class ReservationCustomerFXMLController implements Initializable {

    @FXML
    private TextField CustomerPhoneTF;
    @FXML
    private TextField CustomerFullNameTF;
    @FXML
    private TextField CustomerEmailTF;
    @FXML
    private TextField CustomerAddressTF;
    @FXML
    private TextField ClubMemberPointsTF;
    @FXML
    private TextField ClubMemberExpiryTF;
    @FXML
    private TextField LastNameTF;
    @FXML
    private TextField LicenseNumberTF;

    /* Variables to store values */
    public String customerPhone;
    public Customer customer;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void NextButtonAction(ActionEvent event) throws IOException {
        
        if (!CustomerEmailTF.getText().isEmpty()) {
            ReservationNavigator.newReserve.setCustomerId(customer.getCustomerId());
            ReservationNavigator.clearVista();
            //CreateReservationNavigator.loadVista(ReservationNavigator.SelectVehicle);
            ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
        } else {
            ReservationNavigator.loadVista(ReservationNavigator.ReservationSummary);
            System.out.println("Please select a customer");
        }

    }

    public void BackButtonAction(ActionEvent event) throws IOException {
        System.out.println("I am inside this.Please help");
        ReservationNavigator.clearVista();
        ReservationNavigator.loadVista(ReservationNavigator.ADDITIONALEQUIPMENTS);
    }

    @FXML
    private void NewCustomerButtonAction(ActionEvent event) {
    }

    @FXML
    private void SearchCustomerButtonAction(ActionEvent event) {
        CustomerFullNameTF.setText("");
        CustomerEmailTF.setText("");
        CustomerAddressTF.setText("");
        LastNameTF.setText("");
        LicenseNumberTF.setText("");
        ClubMemberExpiryTF.setText("");
        ClubMemberPointsTF.setText("");
        customerPhone = CustomerPhoneTF.getText();
        CustomerCtrl newCustomerCtrl = new CustomerCtrl();
        customer = newCustomerCtrl.getCustomerByPhone(customerPhone);
        if (customer != null) {
            CustomerFullNameTF.setText(customer.getFirstName());
            CustomerEmailTF.setText(customer.getEmail());
            CustomerAddressTF.setText(customer.getAddress());
            LastNameTF.setText(customer.getLastName());
            LicenseNumberTF.setText(customer.getDriverLicenseNumber());
            if (customer.getIsClubMember()) {
                ClubMemberExpiryTF.setText(customer.getMembershipExpiry().toString());
                ClubMemberPointsTF.setText(Integer.toString(customer.getPoint()));
            }
        } else {
            System.out.println("Customer does not exist");
        }
    }

}
