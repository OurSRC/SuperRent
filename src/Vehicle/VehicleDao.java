/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import Dao.EntityParser.StringParser;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.EnumParser;
import Dao.EntityParser.DateParser;
import Dao.EntityParser.IntParser;
import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * This class provides basic access methods for vehicle entity</p>
 */
public class VehicleDao extends AbstractDao<Vehicle> {

    public static final String tb_name = "vehicle";

    public static final AttributeParser ap[] = {
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

    public static final int[] pkIndex = {0};

    public static final boolean pkIsAutoGen = true;

    @Override
    protected Vehicle getInstance() {
        return new Vehicle();
    }

    /**
     * This method find a {@link Vehicle} available vehicle for rent
     * @return status and rent status
     * @throws DaoException
     */
    public ArrayList<Vehicle> findAvailableForRent() throws DaoException {
        String cond;
        cond = "Status=" + SqlBuilder.wrapInt(Vehicle.STATUS.FORRENT.getValue());
        cond += " AND RentStatus=" + SqlBuilder.wrapInt(Vehicle.RENTSTATUS.IDLE.getValue());
        return find(cond);
    }

    /**
     * This method find a {@link Vehicle} available vehicle for sale
     * @return status and sell status
     * @throws DaoException
     */
    public ArrayList<Vehicle> findAvailableForSale() throws DaoException {
        String cond;
        cond = "Status=" + SqlBuilder.wrapInt(Vehicle.STATUS.FORSALE.getValue());
        cond += " AND SellStatus=" + SqlBuilder.wrapInt(Vehicle.SELLSTATUS.FORSALE.getValue());
        return find(cond);
    }

    /**
     * This method find a {@link Vehicle} with given {@code plateNo}
     * @param plateNo the number of plate
     * @return find one vehicle by plate number
     * @throws DaoException
     */
    public Vehicle findByPlateNo(String plateNo) throws DaoException {
        return findOne("PlateNo=" + SqlBuilder.wrapStr(plateNo));
    }

    /**
     * This method find a {@link Vehicle} with given {@code vehicleNo}
     * @param vehicleNo the number of vehicle
     * @return find one vehicle by vehicle number 
     * @throws DaoException
     */
    public Vehicle findByVehicleNo(int vehicleNo) throws DaoException {
        return findOne("VehicleNo=" + SqlBuilder.wrapInt(vehicleNo));
    }
    
    /**
     * This method find a {@link Vehicle} with given {@code vehicleNo, Mode, manufactureDate, Odometer, price}
     * @param VehicleNo the number of vehicle
     * @param Mode
     * @param manufactureDate
     * @param Odometer
     * @param price
     * @return array list of vehicles
     * @throws DaoException
     */
    public ArrayList<Vehicle> findByVehicleModel(int VehicleNo,String Mode,java.util.Date manufactureDate,int Odometer,int price) throws DaoException{
        String cond;
        cond="VehicleNo="+SqlBuilder.wrapInt(VehicleNo);
        cond+="AND Model="+SqlBuilder.wrapStr(Mode);
        cond+="AND ManufactureDate="+SqlBuilder.wrapDate(manufactureDate);
        cond+="AND Odometer"+SqlBuilder.wrapInt(Odometer);
        cond+="AND SalePrice"+SqlBuilder.wrapInt(price);
        return find(cond);
    }
    
    /**
     * This method find a {@link Vehicle} with given {@code year, className, type}
     * @param year
     * @param className the name of class
     * @param type
     * @return vehicle which is older than the given manufacture date
     * @throws DaoException
     */
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
            
    /**
     * This method counts the vehicle number with given {@code v}
     * @param v
     * @return vehicle number
     */
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
