/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbconn;

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author lenovo
 */
public class SqlBuilder {
    
    private String select;
    private String from;
    private String where;
    private String update;
    private String set;
    private String insert;
    private String values;
    private String delete;
    
    public SqlBuilder() {
        select = null;
        from = null;
        where = null;
        update = null;
        set = null;
        insert = null;
        values = null;
        delete = null;
    }
    
    public SqlBuilder select(String col_name) {
        if (select == null) {
            select = new String("SELECT");
            select += " " + col_name;
        } else {
            select += ", " + col_name;
        }
        
        return this;
    }
    
    public SqlBuilder from(String tb_name) {
        if (from == null) {
            from = new String("FROM");
            from += " " + tb_name;
        } else {
            from += ", " + tb_name;
        }
        
        return this;
    }
    
    public SqlBuilder where(String cond) {
        if (where == null) {
            where = new String("WHERE");
            where += " " + cond;
        } else {
            where += ", " + cond;
        }
        
        return this;
    }
    
    public SqlBuilder update(String tb_name) {
        update = new String("UPDATE " + tb_name);
        return this;
    }
    
    public SqlBuilder set(String set_str) {
        if (set == null) {
            set = "SET " + set_str;
        } else {
            set += " ," + set_str;
        }
        return this;
    }
    
    public SqlBuilder insert(String tb_name) {
        insert = new String("INSERT INTO " + tb_name);
        return this;
    }
    
    public SqlBuilder columns(String... columns) {
        String column = null;
        for (String str : columns) {
            if (column == null) {
                column = str;
            } else {
                column += ", " + str;
            }
        }
        
        insert += "(" + column + ")";
        return this;
    }
    
    public SqlBuilder values(String... strs) {
        for (String str : strs) {
            if (values == null) {
                values = str;
            } else {
                values += ", " + str;
            }
        }
        
        return this;
    }
    
    public SqlBuilder deleteFrom(String tb_name) {
        delete = new String("DELETE FROM " + tb_name);
        return this;
    }
    
    public String toString() {
        if (select != null) {
            return select + " " + from + " " + where + ";";
        } else if (update != null) {
            return update + " " + set + " " + where + ";";
        } else if (insert != null) {
            return insert + " VALUES (" + values + ");";
        } else if (delete != null) {
            return delete + " " + where + ";";
        }
        
        return null;
    }
    
    public void reset() {
        select = null;
        from = null;
        where = null;
    }
    
    public static String wrapStr(String str) {
        if (str == null) {
            return "null";
        }
        return "'" + str + "'";
    }
    
    public static String wrapInt(int i) {
        return Integer.toString(i);
    }
    
    public static String wrapDate(java.util.Date d) {
        if (d == null) {
            return "null";
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String ans = df.format(d);
        return ans;
    }
    
    public static String wrapBool(boolean b) {
        return b? "1" : "0";
    }
    
}
