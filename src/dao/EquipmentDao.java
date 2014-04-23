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
 *
 * @author Xi Yang
 */
public class EquipmentDao extends AbstractDao<Equipment>{
    protected static final String tb_name = "equipment";

    protected static final AttributeParser ap[] = {
        new IntParser("EquipmentId", "EquipmentId"),
        new StringParser("EquipmentType", "EquipmentType"),
        new EnumParser("Status", "Status"),
        new StringParser("Manufactor", "Manufactor"),
        new DateParser("ManufactureDate", "ManufactorDate"),
        new StringParser("Mode", "Mode"),
        new IntParser("BranchId", "BranchId"),
    };
    protected static final int[] pkIndex = {0};
    protected static final boolean pkIsAutoGen = true;
    
    /**
     * 
     * @param id id of Equipment
     * @return Equipment with id, null if non found
     * @throws DaoException 
     */
    public Equipment findEquipmentById(int id) throws DaoException{
        return findOne("EquipmentId ="+ SqlBuilder.wrapInt(id));
    }

    public ArrayList<Equipment> findEquipmentByType(String equipmentType) throws DaoException{
        return find("EquipmentType ="+ SqlBuilder.wrapStr(equipmentType));
    }

    @Override
    protected Equipment getInstance() {
        return new Equipment();
    }
    
    public int countEquipment(Equipment equipment) throws DaoException{
        return countEquipment(equipment.getBranchId(), equipment.getEquipmentType());
    }
    
    public int countEquipment(int branchId, String equipmentType) throws DaoException {
        String cond;
        SqlBuilder qb = new SqlBuilder();
        qb.cond("BranchId=" + SqlBuilder.wrapInt(branchId));
        qb.cond("EquipmentType=" + SqlBuilder.wrapStr(equipmentType));
        cond = qb.toString();
        return count(cond);
    }
}
