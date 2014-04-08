/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityParser;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;

/**
 * This class provide functionalities to transform attributes between database and entity object
 * @author Jingchuan Chen
 */
public class EntityParser {

    /**
     * Parse ResultSet generated by query into entity object based on meta informaiton
     * @param rs Result set
     * @param entity Entity object to populate
     * @param ma MetaInfo array of the entity class
     * @throws ParserException
     */
    public static void parseEntity(ResultSet rs, Object entity, MetaInfo ma[]) throws ParserException {
        for (MetaInfo m : ma) {
            try {
                Object args;
                args = (String) rs.getClass().getMethod("get" + m.getResult_set_type(), String.class).invoke(rs, m.getColumn_name());

                entity.getClass().getMethod("set" + m.getAttr_name(), m.getSetter_arg_cls()).invoke(entity, args);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new ParserException("parserEntity error:" + m.getAttr_name());

            }
        }
    }

    /**
     * Convert attributes of entity to database acceptable string
     * @param entity Entity to convert
     * @param m MetaInfo of an attribute
     * @return Parsed string
     * @throws ParserException
     */
    public static String wrapAttr(Object entity, MetaInfo m) throws ParserException {
        String str = null;
        Object obj;
        try {
            obj = entity.getClass().getMethod("get" + m.getAttr_name()).invoke(entity);

            switch (m.getWrap_type()) {
                case INT: // parse int to string
                    str = ((Integer) obj).toString();
                    break;
                case STRING: // parse "string" to "'string'"
                    str = "'" + ((String) obj) + "'";
                    break;
                case ENUM: // parse enum to int then to string
                    str = ((Integer) obj.getClass().getMethod("getValue").invoke(obj)).toString();
                    break;
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new ParserException("wrapEntity error:" + m.getAttr_name());
        }

        return str;
    }

}
