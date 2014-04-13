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
 *
 * @author Xi Yang
 */
public class SupportEquipmentDao extends AbstractDao<SupportEquipment>{
    protected static final String tb_name = "support";
    protected static final AttributeParser ap[] = {
        new StringParser("EquipmentType", "equipmentType"),
        new StringParser("VehicleClassName", "vehicleClass"),
    };
    
    protected static final int[] pkIndex = {0,1};
    
    protected static final boolean pkIsAutoGen = false;
    
    public SupportEquipment findSupportEquipmentByEquipmenttype(String type) throws DaoException{
        return findOne("EquipmentType="+SqlBuilder.wrapStr(type));
    }
    public SupportEquipment findEquipmentTypeByVehicleclassname(String className) throws DaoException{
        return findOne("VehicleClassName="+SqlBuilder.wrapStr(className));
    }
    public ArrayList<SupportEquipment> findEquipmentByType(String type,String className) throws DaoException{
        return find("Support ="+ SqlBuilder.wrapStr(type)+ SqlBuilder.wrapStr(className));
    }
    
    public ArrayList<SupportEquipment> findByVehicleClass(String vehicleClass) throws DaoException{
        return find("VehicleClassName ="+ SqlBuilder.wrapStr(vehicleClass));
    }
    public ArrayList<SupportEquipment> findByEquipmentType(String equipmentType) throws DaoException{
        return find("EquipmentType ="+ SqlBuilder.wrapStr(equipmentType));
    }
    @Override
    protected SupportEquipment getInstance() {
        return new SupportEquipment();
    }
}
