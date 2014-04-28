/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityParser;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;

/**
 * This class represent a mapping between String field in entity object and
 * Varchar field in database.
 */
public class StringParser extends AttributeParser {

    public StringParser(String colName, String attrName) {
        super(colName, attrName);
    }

    @Override
    protected void setAttrEx(ResultSet rs, Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        // call rs.getString("column name");
        String args = (String) rs.getClass().getMethod("getString", String.class).invoke(rs, getColName());
        // call entity.setAttr(args);
        entity.getClass().getMethod("set" + getAttrName(), String.class).invoke(entity, args);
    }

    @Override
    protected String wrapAttrEx(Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        String obj = (String) entity.getClass().getMethod("get" + getAttrName()).invoke(entity);
        if (obj == null) {
            return "NULL";
        }
        return "'" + obj + "'";
    }

    @Override
    protected void setAttrByValEx(Object entity, Object value) throws NoSuchMethodException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
