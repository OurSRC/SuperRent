/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.EntityParser;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;

/**
 * This class represent a mapping between int field in entity object and int
 * field in database. The int field is null in database if its value is 0.
 */
public class IntNullableParser extends AttributeParser {

    @Override
    protected void setAttrEx(ResultSet rs, Object entity) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        // call rs.getInt("column name");
        int args = (int) rs.getClass().getMethod("getInt", String.class).invoke(rs, getColName());
        // call entity.setAttr(args);
        entity.getClass().getMethod("set" + getAttrName(), int.class).invoke(entity, args);
    }

    @Override
    protected String wrapAttrEx(Object entity) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        int obj = (int) entity.getClass().getMethod("get" + getAttrName()).invoke(entity);

        if (obj == 0) {
            return "null";
        }

        return Integer.toString(obj);
    }

    @Override
    protected void setAttrByValEx(Object entity, Object value) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        entity.getClass().getMethod("set" + getAttrName(), int.class).invoke(entity, value);
    }

    public IntNullableParser(String colName, String attrName) {
        super(colName, attrName);
    }

}
