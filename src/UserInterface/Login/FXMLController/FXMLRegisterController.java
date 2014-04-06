package UserInterface.Login.FXMLController;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Vyas
 */
public class FXMLRegisterController {
    public RadioButton ExistingCustomerRadio;
    public TextField MobileNumberTF;
    public TextField NameTF;
    public Label NameLabel;
    public TextField AddressTF;
    public Label AddressLabel;
    public TextField EmailTF;
    public Label EmailLabel;
    public TextField DOBTF;
    public Label DOBLabel;
    public Label InputWarning;
    public TextField UsernameTF;
    public TextField PasswordTF;
    public TextField ConfirmPasswordTF;
    public TextField LastNameTF;
    public TextField PinCodeTF;
    public TextField CityTF;
    public Label CityLabel;
    public Label PinCodeLabel;
    public Label LastNameLabel;
    @FXML
    private RadioButton NewCustomerRadio;
    @FXML
    private ToggleGroup CustomerGroup;
    @FXML
    private Label MobileLabel;
    @FXML
    private Label UserNameExistsLabel;
    
    @FXML
    private void NewCustomerRadioAction(ActionEvent event) {
        NameTF.setEditable(true);
        NameLabel.setOpacity(1);
        AddressTF.setEditable(true);
        AddressLabel.setOpacity(1);
        EmailTF.setEditable(true);
        EmailLabel.setOpacity(1);
        LastNameLabel.setOpacity(1);
        CityLabel.setOpacity(1);
        PinCodeLabel.setOpacity(1);
        LastNameTF.setEditable(true);
        CityTF.setEditable(true);
        PinCodeTF.setEditable(true);
    }
    
    @FXML
    private void ExistingCustomerRadioAction(ActionEvent event) {
        NameTF.setEditable(false);
        NameLabel.setOpacity(.5);
        AddressTF.setEditable(false);
        AddressLabel.setOpacity(.5);
        EmailTF.setEditable(false);
        EmailLabel.setOpacity(.5);
        LastNameLabel.setOpacity(.5);
        CityLabel.setOpacity(.5);
        PinCodeLabel.setOpacity(.5);
        LastNameTF.setEditable(false);
        CityTF.setEditable(false);
        PinCodeTF.setEditable(false);
        NameTF.setText(null);
        AddressTF.setText(null);
        EmailTF.setText(null);
        LastNameTF.setText(null);
        CityTF.setText(null);
        PinCodeTF.setText(null);
    }
    
    @FXML
    private void ResetCustomerFieldsAction(ActionEvent event) {
        NameTF.setText(null);
        AddressTF.setText(null);
        EmailTF.setText(null);
    }
    
    @FXML
    public void RegisterButtonAction(ActionEvent event) throws Exception {
        
        if(ExistingCustomerRadio.isSelected())
        {
        String MobileNumber = MobileNumberTF.getText();
        String Username =  UsernameTF.getText();
        String password = PasswordTF.getText();
        String confirmPassword = ConfirmPasswordTF.getText();
        //String CustomerFullName = NameTF.getText();
        /*String Address = AddressTF.getText();
        String Email = EmailTF.getText();
        String LastName = LastNameTF.getText();
        String City = CityTF.getText();
        String PinCode =  PinCodeTF.getText();*/
/*        RegisterControl registerControl = new RegisterControl(MobileNumber);
        if(registerControl.CheckExistingCustomer())
        {
           System.out.println("Customer record exists");
        }else
        {
            System.out.println("No Customer record found");
        }
        }
        
        RegisterControl registerControl = new RegisterControl();*/
    }
    }
    
    @FXML
    public void ValidateIfInteger(KeyEvent event)
    {
        /*
        System.out.println(event.getCharacter().substring(0,MobileNumber.getText().length()));
        InputWarning.setVisible(false);
        System.out.println(event.getCharacter());
        
        if(event.getCharacter().equals("g"))
        {
            //String NewString = MobileNumber.getText(1, MobileNumber.getText().length());
            String replace = MobileNumber.getText().replace(event.getCharacter(), "");
            InputWarning.setVisible(true);
            MobileNumber.clear();

            MobileNumber.setText(replace);

            System.out.println(replace);
        }*/
           // MobileNumber.setText(replace);           // MobileNumber.setText(NewString);
            //System.out.println(event.getText());
            // MobileNumber.setText();
        
    }
    
}