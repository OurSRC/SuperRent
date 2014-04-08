/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityParser;

/**
 * This class models meta information of a attribute in an entity class.
 * @author Jingchuan Chen
 */
public class MetaInfo {

    public enum WrapType {
        INT, STRING, ENUM, BOOLEAN, DATE
    }

    private String column_name;         // column name in database
    private String attr_name;           // attr name in entity object
    private String result_set_type;     // type used in resulrSet. e.g. "String" means use rs.getString()
    private Class setter_arg_cls;       // argument type of setter 
    private WrapType wrap_type;         // indicate type of return value of getter

    public MetaInfo(String column_name, String attr_name, String type_name, Class cls, WrapType wrap_type) {
        this.column_name = column_name;
        this.attr_name = attr_name;
        this.result_set_type = type_name;
        this.setter_arg_cls = cls;
        this.wrap_type = wrap_type;
    }

    /**
     * @return the column_name
     */
    public String getColumn_name() {
        return column_name;
    }

    /**
     * @param column_name the column_name to set
     */
    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    /**
     * @return the attr_name
     */
    public String getAttr_name() {
        return attr_name;
    }

    /**
     * @param attr_name the attr_name to set
     */
    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    /**
     * @return the result_set_type
     */
    public String getResult_set_type() {
        return result_set_type;
    }

    /**
     * @param result_set_type the result_set_type to set
     */
    public void setResult_set_type(String result_set_type) {
        this.result_set_type = result_set_type;
    }

    /**
     * @return the setter_arg_cls
     */
    public Class getSetter_arg_cls() {
        return setter_arg_cls;
    }

    /**
     * @param setter_arg_cls the setter_arg_cls to set
     */
    public void setSetter_arg_cls(Class setter_arg_cls) {
        this.setter_arg_cls = setter_arg_cls;
    }

    /**
     * @return the wrap_type
     */
    public WrapType getWrap_type() {
        return wrap_type;
    }

    /**
     * @param wrap_type the wrap_type to set
     */
    public void setWrap_type(WrapType wrap_type) {
        this.wrap_type = wrap_type;
    }
    
    
}
