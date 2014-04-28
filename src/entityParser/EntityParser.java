/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityParser;

import java.sql.ResultSet;

/**
 * This class provide functionalities to transform attributes between database
 * and entity object
 */
public class EntityParser {

    /**
     * Read data from ResultSet, and populate entity object based on defined
     * attributeParsers. This method process one result in resultset only,
     * ResultSet.next() need to be called manually.
     *
     * @param rs ResultSet with results
     * @param entity Entity object to populate
     * @param ap AttributeParsers that defines mapping between database and
     * entity object
     */
    public static void parseEntity(ResultSet rs, Object entity, AttributeParser ap[]) {
        for (AttributeParser parser : ap) {
            parser.setAttr(rs, entity);
        }
    }

    /**
     * Convert attributes in entity to sql representation to manipulate database
     *
     * @param entity Entity object containing data to convert
     * @param ap AttributeParsers that specifies database and entity mapping
     * @return Array of string with converted Strings
     */
    public static String[] wrapEntity(Object entity, AttributeParser ap[]) {
        String result[] = new String[ap.length];
        for (int i = 0; i < ap.length; i++) {
            result[i] = ap[i].wrapAttr(entity);
        }

        return result;
    }

    /**
     * Generate set expression that used to update database. E.g. If attribute
     * "password" of value "abc" in entity class has correspond database column
     * "Password", it will generate "Password='abc'"
     *
     * @param entity Entity object with attributes to convert
     * @param ap AttributeParser specified mapping
     * @return String of converted set expression
     */
    public static String[] wrapEntityForSet(Object entity, AttributeParser ap[]) {
        String result[] = new String[ap.length];
        for (int i = 0; i < ap.length; i++) {
            result[i] = ap[i].getColName() + "=" + ap[i].wrapAttr(entity);
        }

        return result;
    }

    /**
     * Return a list of database column names.
     *
     * @param ap AttributeParsers contains database column names.
     * @return String array of column names.
     */
    public static String[] getColunmList(AttributeParser ap[]) {
        String result[] = new String[ap.length];
        for (int i = 0; i < ap.length; i++) {
            result[i] = ap[i].getColName();
        }

        return result;
    }
}
