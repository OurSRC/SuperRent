/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.VehicleClass;
import entityParser.*;
import java.util.ArrayList;


public class VehicleClassDao extends AbstractDao<VehicleClass> {
    protected static final String tb_name = "vehicle_class";
    
    protected static final AttributeParser ap[] = {
        new StringParser("ClassName", "ClassName"),
        new EnumParser("Type", "VehicleType"),
        new IntParser("HourlyRate", "HourlyRate"),
        new IntParser("DailyRate", "DailyRate"),
        new IntParser("WeeklyRate", "WeeklyRate"),
    };
    
    protected static final int pkIndex = 0;
    
    protected static final boolean pkIsAutoGen = false;
    
    @Override
    protected VehicleClass getInstance() {
        return new VehicleClass();
    }
    
    public VehicleClass findByName(String name) throws DaoException {
        return findOne("ClassName=" + SqlBuilder.wrapStr(name));
    }
    
    public ArrayList<VehicleClass> findByClass(VehicleClass.TYPE type) throws DaoException {
        return find("Type=" + SqlBuilder.wrapInt(type.getValue()));
    }
}
