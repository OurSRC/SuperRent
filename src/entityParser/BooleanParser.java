/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityParser;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;

/**
 * This class represent a mapping between boolean field in entity object and in database.
 * @author Jingchuan Chen
 */
public class BooleanParser extends AttributeParser {

    @Override
    protected void setAttrEx(ResultSet rs, Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        // call rs.getBoolean("column name");
        boolean args = (boolean) rs.getClass().getMethod("getBoolean", String.class).invoke(rs, getColName());
        // call entity.setAttr(args);
        entity.getClass().getMethod("set" + getAttrName(), boolean.class).invoke(entity, args);
    }

    @Override
    protected String wrapAttrEx(Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        
        boolean obj = (boolean) entity.getClass().getMethod("get" + getAttrName()).invoke(entity);
        return obj ? "1" : "0";
    }

    /**
     * Create a mapping between boolean entity field and database field.
     * @param colName
     * @param attrName
     */
    public BooleanParser(String colName, String attrName) {
        super(colName, attrName);
    }

    @Override
    protected void setAttrByValEx(Object entity, Object value) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
