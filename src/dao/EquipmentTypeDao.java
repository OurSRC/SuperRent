/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.EquipmentType;
import entityParser.*;
import java.util.ArrayList;

/**
 * <p>
 *  This class provides basic access methods, for example, find
 *  for equipmentType entity.</p>
 * @author Xi Yang
 */

public class EquipmentTypeDao extends AbstractDao<EquipmentType>{
    protected static final String tb_name = "equipment_type";
    
    protected static final AttributeParser ap[] = {
        new StringParser("TypeName", "TypeName"),
        new IntParser("DailyRate", "DailyRate"),
        new IntParser("HourlyRate", "HourlyRate")
            
    };
    
    protected static final int[] pkIndex = {0};
    
    protected static final boolean pkIsAutoGen = false;
    
    /**
     * This method find a {@link EquipmentType} with given {@code typeName}
     * @param typeName typeName of Equipment
     * @return Equipment with id, null if non found
     * @throws DaoException 
     */
    public EquipmentType findEquipmentTypeByTypename(String typeName) throws DaoException{
        return findOne("TypeName="+SqlBuilder.wrapStr(typeName));
    }

    /**
     *
     * @return
     */
    @Override
    protected EquipmentType getInstance() {
        return new EquipmentType();
    }
}
