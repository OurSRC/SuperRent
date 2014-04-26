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
 * This class provide functionalities to construct SqlQuery
 * @author Jingchuan Chen
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
    private String columns;
    private String cond;
    private boolean subQue;

    public SqlBuilder() {
        select = null;
        from = null;
        where = null;
        update = null;
        set = null;
        insert = null;
        values = null;
        delete = null;
        cond = null;
        subQue = false;
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
        if (cond == null) {
            return this;
        }

        if (where == null) {
            where = new String("WHERE");
            where += " " + cond;
        } else {
            where += " AND " + cond;
        }

        return this;
    }

    public SqlBuilder update(String tb_name) {
        update = new String("UPDATE " + tb_name);
        return this;
    }
    /*
     public SqlBuilder set(String set_str) {
     if (set == null) {
     set = "SET " + set_str;
     } else {
     set += " ," + set_str;
     }
     return this;
     }*/

    public SqlBuilder set(String... strs) {
        for (String str : strs) {
            if (set == null) {
                set = "SET " + str;
            } else {
                set += ", " + str;
            }
        }
        return this;
    }

    public SqlBuilder insert(String tb_name) {
        insert = new String("INSERT INTO " + tb_name);
        return this;
    }

    public SqlBuilder columns(String... col_strs) {
        for (String str : col_strs) {
            if (columns == null) {
                columns = str;
            } else {
                columns += ", " + str;
            }
        }
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

    public SqlBuilder isSubQueue() {
        subQue = true;
        return this;
    }

    public String toString() {
        if (select != null) {
            String str;

            if (where == null) {
                str = select + " " + from;
            } else {
                str = select + " " + from + " " + where;
            }
            
            if (subQue) {
                return str;
            } else {
                return str + ";";
            }
            
        } else if (update != null) {
            return update + " " + set + " " + where + ";";
        } else if (insert != null) {
            if (columns != null) {
                insert += "(" + columns + ")";
            }
            return insert + " VALUES (" + values + ");";
        } else if (delete != null) {
            return delete + " " + where + ";";
        } else if (cond != null) {
            return cond;
        }

        return null;
    }

    public void cond(String str) {
        if (cond == null) {
            cond = str;
        } else {
            cond += " AND " + str;
        }
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

    public static String wrapDatetime(java.util.Date d) {
        if (d == null) {
            return "null";
        }
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        String ans = df.format(d);
        return wrapStr(ans);
    }

    public static String wrapDate(java.util.Date d) {
        if (d == null) {
            return "null";
        }
        DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
        String ans = df.format(d);
        return wrapStr(ans);
    }

    public static String wrapBool(boolean b) {
        return b ? "1" : "0";
    }

}
