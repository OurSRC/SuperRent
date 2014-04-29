/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vehicle;

import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.IntParser;
import Dao.EntityParser.StringParser;
import java.util.ArrayList;

/**
 * <p>
 *  This class provides basic access methods, for example, find, match
 *  for supportEquipment entity.</p>
 */
public class SupportEquipmentDao extends AbstractDao<SupportEquipment>{
    public static final String tb_name = "support";
    public static final AttributeParser ap[] = {
        new StringParser("EquipmentType", "EquipmentType"),
        new StringParser("VehicleClassName", "VehicleClass"),
    };
    
    public static final int[] pkIndex = {0,1};
    
    public static final boolean pkIsAutoGen = false;
   
    /**
     * This method find boolean value with given {@code vehicleClass, equipmentType}
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
     * @return array list of vehicle class names, null if non found
     * @throws DaoException
     */
    public ArrayList<SupportEquipment> findByVehicleClass(String vehicleClass) throws DaoException{
        return find("VehicleClassName ="+ SqlBuilder.wrapStr(vehicleClass));
    }

    /**
     * This method find a {@link SupportEquipment} with given {@code equipmentType}
     * @param equipmentType equipment type of equipment
     * @return array list of equipment types, null if non found
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
