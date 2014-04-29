/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao.EntityParser;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contain methods for transformation between attribute in entity
 * class and column in database. Each instance indicate a mapping between column
 * and attribute. This is a abstract class, each type correspond to a concrete
 * type.
 */
public abstract class AttributeParser {

    private String colName;
    private String attrName;

    /**
     * Initializer
     *
     * @param colName Column name in database
     * @param attrName Capitalized attribute name in entity, or name of
     * getter/setter without "get" or "set". E.g. Entity class E has an
     * attribute foo, with getter getFoo() and setter setFoo, attrName shoule be
     * "Foo".
     */
    public AttributeParser(String colName, String attrName) {
        this.colName = colName;
        this.attrName = attrName;
    }

    /**
     * Retrieve data from ResultSet, call setter function of entity object to
     * set attribute. This function calls setAttrEx method of subclass, and
     * handle exception. IMPORTANT: for entity object with Enum type, there must
     * have a setter that support String type. Example:
     * <pre>
     * {@code
     * #regular setter for enum
     * public void setType(TYPE type) {
     *   this.Type = type;
     * }
     * # setter that support String
     * public void setType(String type) {
     *   this.Type = TYPE.valueOf(type);
     * }
     * }
     * </pre>
     *
     * @param rs Result set with query result
     * @param entity Entity object to populate from result set
     */
    public void setAttr(ResultSet rs, Object entity) {
        try {
            setAttrEx(rs, entity);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            Logger.getLogger(AttributeParser.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }
    }

    /**
     * Convert attribute on entity object to database acceptable format. E.g.
     * Integer 1 to "1" String "abc" to "'abc'" Boolean true to "1" Enum to
     * database enum value. Important: Enum type must have getValue() method the
     * generate value corresponding to Database ENUM value.
     *
     * @param entity
     * @return Result string after convert
     */
    public String wrapAttr(Object entity) {
        String str = null;
        try {
            str = wrapAttrEx(entity);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            Logger.getLogger(AttributeParser.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }
        return str;
    }

    /**
     * Set the corresponding attribute of entity object to value.
     *
     * @param entity The entity object with field to be set.
     * @param value Value of the field.
     */
    public void setAttrByVal(Object entity, Object value) {
        try {
            setAttrByValEx(entity, value);
        } catch (IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException ex) {
            Logger.getLogger(AttributeParser.class.getName()).log(Level.SEVERE, null, ex);
            //System.exit(1);
        }
    }

    protected abstract void setAttrEx(ResultSet rs, Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException;

    protected abstract String wrapAttrEx(Object entity) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException;

    protected abstract void setAttrByValEx(Object entity, Object value) throws NoSuchMethodException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException;

    /**
     * @return the colName
     */
    public String getColName() {
        return colName;
    }

    /**
     * @return the attrName
     */
    public String getAttrName() {
        return attrName;
    }
}
