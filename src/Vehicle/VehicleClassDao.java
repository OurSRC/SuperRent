/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vehicle;

import Dao.EntityParser.StringParser;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.EnumParser;
import Dao.EntityParser.IntParser;
import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;
import java.util.ArrayList;

/**
 * <p>
 * This class provides basic access methods for vehicle class entity</p>
 */
public class VehicleClassDao extends AbstractDao<VehicleClass> {
    protected static final String tb_name = "vehicle_class";
    
    protected static final AttributeParser ap[] = {
        new StringParser("ClassName", "ClassName"),
        new EnumParser("Type", "VehicleType"),
        new IntParser("HourlyRate", "HourlyRate"),
        new IntParser("DailyRate", "DailyRate"),
        new IntParser("WeeklyRate", "WeeklyRate"),
    };
    
    protected static final int[] pkIndex = {0};
    
    protected static final boolean pkIsAutoGen = false;
    
    @Override
    protected VehicleClass getInstance() {
        return new VehicleClass();
    }
    
    /**
     * This method find a {@link VehicleClass} by name with given {@code name}
     * @param name
     * @return
     * @throws DaoException
     */
    public VehicleClass findByName(String name) throws DaoException {
        return findOne("ClassName=" + SqlBuilder.wrapStr(name));
    }
    
    /**
     * This method find a {@link VehicleClass} by class with given {@code type}
     * @param type
     * @return array list of vehicle class
     * @throws DaoException
     */
    public ArrayList<VehicleClass> findByClass(VehicleClass.TYPE type) throws DaoException {
        return find("Type=" + SqlBuilder.wrapInt(type.getValue()));
    }
}
