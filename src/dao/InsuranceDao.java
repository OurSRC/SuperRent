/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import dbconn.SqlBuilder;
import entity.Insurance;
import entityParser.*;
import java.util.ArrayList;

/**
 * <p>
 * This class provides basic access methods, for example, find
 * for equipmentType entity.</p>
 */
public class InsuranceDao extends AbstractDao<Insurance> {
    
    protected static final String tb_name = "insurance";

    protected static final AttributeParser ap[] = {
        new StringParser("Name", "Name"),
        new IntParser("HourlyRate", "HourlyRate"),
        new IntParser("DailyRate", "DailyRate"),
        new IntParser("WeeklyRate", "WeeklyRate"),
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = false;
    

    @Override
    protected Insurance getInstance() {
        return new Insurance();
    }
    
    public Insurance findByName(String insuranceName) throws DaoException {
        String cond = "Name = " + SqlBuilder.wrapStr(insuranceName);
        return findOne(cond);
    }

    public ArrayList<Insurance> findAll() throws DaoException {
        return all();
    }
}
