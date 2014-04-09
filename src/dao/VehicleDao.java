/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Vehicle;
import entityParser.*;


public class VehicleDao extends AbstractDao<Vehicle> {
    
    protected static final String tb_name = "vehicle";

    protected static final AttributeParser ap[] = {
        new IntParser("VehicleNo", "VehicleNo"),
        new StringParser("PlateNo", "PlateNo"),
        new DateParser("ManufactureDate", "ManufactureDate"),
        new StringParser("Mode", "Mode"),
        new IntParser("Odometer", "Odometer"),
        new IntParser("BranchID", "BranchId"),
        new EnumParser("Status", "Status"),
        new EnumParser("RentStatus", "RentStatus"),
        new StringParser("ClassName", "ClassName"),
        new IntParser("Price", "Price"),
        new EnumParser("SellStatus", "SellStatus")
    };
    
    protected static final int pkIndex = 0;
    
    protected static final boolean pkIsAutoGen = true;
    

    @Override
    protected Vehicle getInstance() {
        return new Vehicle();
    }
    
}
