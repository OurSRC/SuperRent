/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.SupportEquipment;
import entityParser.AttributeParser;
import entityParser.IntParser;
import entityParser.StringParser;
import java.util.ArrayList;

/**
 * <p>
 *  This class provides basic access methods, for example, find, match
 *  for supportEquipment entity.</p>
 * @author Xi Yang
 */
public class SupportEquipmentDao extends AbstractDao<SupportEquipment>{
    protected static final String tb_name = "support";
    protected static final AttributeParser ap[] = {
        new StringParser("EquipmentType", "EquipmentType"),
        new StringParser("VehicleClassName", "VehicleClass"),
    };
    
    protected static final int[] pkIndex = {0,1};
    
    protected static final boolean pkIsAutoGen = false;
   
    /**
     * This method find a {@link boolean} with given {@code vehicleClass, equipmentType}
     * @param vehicleClass vehicle class of vehicle
     * @param equipmentType type of equipment
     * @return true or false
     * @throws DaoException
     */
    public boolean matchVehicleClassAndEquipmentType(String vehicleClass,String equipmentType) throws DaoException{
        String cond;
        cond = "VehicleClassName=" + SqlBuilder.wrapStr(vehicleClass);
        cond += " AND EquipmentType=" + SqlBuilder.wrapStr(equipmentType);
        if( findOne(cond)!=null )
            return true;
        else
            return false;
    }
    
    /**
     * This method find a {@link SupportEquipment} with given {@code vehicleClass}
     * @param vehicleClass vehicle class of vehicle
     * @return vehicle class name, null if non found
     * @throws DaoException
     */
    public ArrayList<SupportEquipment> findByVehicleClass(String vehicleClass) throws DaoException{
        return find("VehicleClassName ="+ SqlBuilder.wrapStr(vehicleClass));
    }

    /**
     * This method find a {@link SupportEquipment} with given {@code equipmentType}
     * @param equipmentType equipment type of equipment
     * @return equipment type, null if non found
     * @throws DaoException
     */
    public ArrayList<SupportEquipment> findByEquipmentType(String equipmentType) throws DaoException{
        return find("EquipmentType ="+ SqlBuilder.wrapStr(equipmentType));
    }
    @Override
    protected SupportEquipment getInstance() {
        return new SupportEquipment();
    }
}
