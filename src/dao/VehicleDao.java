/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.SqlBuilder;
import entity.Vehicle;
import entity.VehicleClass;
import entityParser.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class provides basic access methods for vehicle entity</p>
 */
public class VehicleDao extends AbstractDao<Vehicle> {

    protected static final String tb_name = "vehicle";

    protected static final AttributeParser ap[] = {
        new IntParser("VehicleNo", "VehicleNo"),
        new StringParser("PlateNo", "PlateNo"),
        new DateParser("ManufactureDate", "ManufactureDate"),
        new StringParser("Model", "Mode"),
        new IntParser("Odometer", "Odometer"),
        new IntParser("BranchID", "BranchId"),
        new EnumParser("Status", "Status"),
        new EnumParser("RentStatus", "RentStatus"),
        new StringParser("ClassName", "ClassName"),
        new IntParser("Price", "Price"),
        new EnumParser("SellStatus", "SellStatus")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = true;

    @Override
    protected Vehicle getInstance() {
        return new Vehicle();
    }

    public ArrayList<Vehicle> findAvailableForRent() throws DaoException {
        String cond;
        cond = "Status=" + SqlBuilder.wrapInt(Vehicle.STATUS.FORRENT.getValue());
        cond += " AND RentStatus=" + SqlBuilder.wrapInt(Vehicle.RENTSTATUS.IDLE.getValue());
        return find(cond);
    }

    public ArrayList<Vehicle> findAvailableForSale() throws DaoException {
        String cond;
        cond = "Status=" + SqlBuilder.wrapInt(Vehicle.STATUS.FORSALE.getValue());
        cond += " AND SellStatus=" + SqlBuilder.wrapInt(Vehicle.SELLSTATUS.FORSALE.getValue());
        return find(cond);
    }

    public Vehicle findByPlateNo(String plateNo) throws DaoException {
        return findOne("PlateNo=" + SqlBuilder.wrapStr(plateNo));
    }

    public Vehicle findByVehicleNo(int vehicleNo) throws DaoException {
        return findOne("VehicleNo=" + SqlBuilder.wrapInt(vehicleNo));
    }
    
    public ArrayList<Vehicle> findByVehicleModel(int VehicleNo,String Mode,java.util.Date manufactureDate,int Odometer,int price) throws DaoException{
        String cond;
        cond="VehicleNo="+SqlBuilder.wrapInt(VehicleNo);
        cond+="AND Model="+SqlBuilder.wrapStr(Mode);
        cond+="AND ManufactureDate="+SqlBuilder.wrapDate(manufactureDate);
        cond+="AND Odometer"+SqlBuilder.wrapInt(Odometer);
        cond+="AND SalePrice"+SqlBuilder.wrapInt(price);
        return find(cond);
    }
    
    public ArrayList<Vehicle> findVehicleOlderThan(int year, String className, 
            VehicleClass.TYPE type) throws DaoException {
        SqlBuilder qb = new SqlBuilder();
        
        if (className != null) {
            qb.cond("ClassName = " + SqlBuilder.wrapStr(className));
        } else if (type != null) {
            SqlBuilder subQb = new SqlBuilder();
            String subQuery = subQb
                    .select("ClassName")
                    .from("vehicle_class")
                    .where("Type = " + SqlBuilder.wrapInt(type.getValue()))
                    .isSubQueue().toString();
            subQuery = "(" + subQuery + ")";
            qb.cond("ClassName IN " + subQuery);
        }
        qb.cond("ManufactureDate < " + SqlBuilder.wrapStr(Integer.toString(year) + "-12-31"));
        return find(qb.toString());
    }
            
            
    public int countVehicle(Vehicle v){ //include int branchId
        ArrayList<Vehicle> list = null;
        try {
            list = findByInstance(v);
        } catch (DaoException ex) {
            Logger.getLogger(VehicleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(list==null)
            return 0;
        else
            return list.size();
    }
}
