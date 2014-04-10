/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package UserInterface.Operations.FXMLController;

import com.sun.javafx.scene.control.skin.DatePickerContent;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Vyas
 */
public class ReserveSample {
    
    
    private StringProperty ReservationNumber;
    private StringProperty Type;
    private StringProperty PickUpDate;
    private StringProperty ReturnDate;
    private StringProperty EstimatedCost;
    
    
    
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
    
    
    public StringProperty PickUpDateProperty() {
        return this.PickUpDate;
    }
    
    
    public void setPickUpDate(String PickUpDate) {
	this.PickUpDate.set(PickUpDate);
    }
    
    public String getPickUpDate() {
	return PickUpDate.get();
    }
    
    
    public StringProperty ReturnDateProperty() {
        return this.ReturnDate;
    }
    
    
    public void setReturnDate(String ReturnDate) {
	this.ReturnDate.set(ReturnDate);
    }
    
    public String getReturnDate() {
	return ReturnDate.get();
    }
    
    
    public StringProperty EstimatedCostProperty() {
        return this.EstimatedCost;
    }
    
    
    public void setEstimatedCost(String EstimatedCost) {
	this.EstimatedCost.set(EstimatedCost);
    }
    
    public String getEstimatedCost() {
	return EstimatedCost.get();
    }
}