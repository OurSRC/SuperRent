/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vyas
 */
public class ReserveSample {
    
    
    private StringProperty ReservationNumber;
    private StringProperty Type;
    
    
    
    ReserveSample(String FName,String Type)
    {
        this.ReservationNumber = new SimpleStringProperty(FName);
        this.Type = new SimpleStringProperty(Type);

    }

    public StringProperty ReservationNumberProperty() {
        return this.ReservationNumber;
    }

    public void setReservationNumber(String ReservationNumber) {
	this.ReservationNumber.set(ReservationNumber);
    }

                
   public String getReservationNumber() {
	return ReservationNumber.get();
   }
    

    public StringProperty TypeProperty() {
        return this.Type;
    }
    
    
    public void setType(String Type) {
	this.Type.set(Type);
    }
    
    public String getType() {
	return Type.get();
   }
}
