/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.EntityParser;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class represent a mapping between Date field in entity object and Date
 * field in database.
 */
public class DateParser extends AttributeParser {

    @Override
    protected void setAttrEx(ResultSet rs, Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        // call rs.getDate("column name");
        Date args = (Date) rs.getClass().getMethod("getDate", String.class).invoke(rs, getColName());
        // call entity.setAttr(args);
        entity.getClass().getMethod("set" + getAttrName(), Date.class).invoke(entity, args);
    }

    @Override
    protected String wrapAttrEx(Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        Date obj = (Date) entity.getClass().getMethod("get" + getAttrName()).invoke(entity);
        if (obj == null) {
            return "NULL";
        }
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String str = "'" + df.format((Date) obj) + "'";
        return str;
    }

    public DateParser(String colName, String attrName) {
        super(colName, attrName);
    }

    @Override
    protected void setAttrByValEx(Object entity, Object value) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
