/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.Equipment;
import entityParser.*;
import java.util.ArrayList;

/**
 * <p>
 *  This class provides basic access methods, for example, find, insert, update, delete
 *  for equipment entity.</p>
 */
public class EquipmentDao extends AbstractDao<Equipment>{
    protected static final String tb_name = "equipment";

    protected static final AttributeParser ap[] = {
        new IntParser("EquipmentId", "EquipmentId"),
        new StringParser("EquipmentType", "EquipmentType"),
        new EnumParser("Status", "Status"),
        new StringParser("Manufactor", "Manufactor"),
        new DateParser("ManufactureDate", "ManufactorDate"),
        new StringParser("Model", "Mode"),
        new IntParser("BranchId", "BranchId"),
    };
    protected static final int[] pkIndex = {0};
    protected static final boolean pkIsAutoGen = true;
    
    /**
     * This method find a {@link Equipment} with given {@code id}
     * @param id id of Equipment
     * @return Equipment with id, null if non found
     * @throws DaoException 
     */
    public Equipment findEquipmentById(int id) throws DaoException{
        return findOne("EquipmentId ="+ SqlBuilder.wrapInt(id));
    }
    /**
     * This method find a {@link Equipment} with given {@code equipmentType}
     * @param equipmentType equipmentType of Equipment
     * @return array list of equipment with equipmentType, null if non found
     * @throws DaoException 
     */  
    public ArrayList<Equipment> findEquipmentByType(String equipmentType) throws DaoException{
        return find("EquipmentType ="+ SqlBuilder.wrapStr(equipmentType));
    }

    /**
     *
     * @return
     */
    @Override
    protected Equipment getInstance() {
        return new Equipment();
    }
    
    /**
     * This method find equipment number with given {@code equipment}
     * @param equipment equipment
     * @return count of equipments
     * @throws DaoException 
     */
    public int countEquipment(Equipment equipment) throws DaoException{
        return countEquipment(equipment.getBranchId(), equipment.getEquipmentType());
    }
    
    /**
     * This method find equipment number with given {@code branchId, equipmentType} 
     * @param branchId branch ID of branch
     * @param equipmentType   equipment type of equipment
     * @return count of equipments
     * @throws DaoException
     */
    public int countEquipment(int branchId, String equipmentType) throws DaoException {
        String cond;
        SqlBuilder qb = new SqlBuilder();
        qb.cond("BranchId=" + SqlBuilder.wrapInt(branchId));
        qb.cond("EquipmentType=" + SqlBuilder.wrapStr(equipmentType));
        cond = qb.toString();
        return count(cond);
    }
}
