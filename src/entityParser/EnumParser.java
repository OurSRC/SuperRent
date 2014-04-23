/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityParser;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;

/**
 * Convey Enum type between attribute and database string. IMPORTANT: To parse
 * an enum object, the entity class's setter method of enum attribute must
 * support String argument. Enum class also need to have
 *
 * @author Jingchuan Chen
 */
public class EnumParser extends AttributeParser {

    @Override
    protected void setAttrEx(ResultSet rs, Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        // call rs.getString("column name");
        String args = (String) rs.getClass().getMethod("getString", String.class).invoke(rs, getColName());
        // call entity.setAttr(args); Enum is set using string
        entity.getClass().getMethod("set" + getAttrName(), String.class).invoke(entity, args);
    }

    @Override
    protected String wrapAttrEx(Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {

        Object obj = entity.getClass().getMethod("get" + getAttrName()).invoke(entity);
        if (obj == null) {
            return "NULL";
        }
        String str = ((Integer) obj.getClass().getMethod("getValue").invoke(obj)).toString();
        return str;
    }

    public EnumParser(String colName, String attrName) {
        super(colName, attrName);
    }

    @Override
    protected void setAttrByValEx(Object entity, Object value) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
