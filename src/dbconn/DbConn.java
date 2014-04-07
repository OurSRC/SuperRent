/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbconn;

import java.sql.*;

/**
 *
 * @author lenovo
 */
public class DbConn {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/super_rent";
    static final String USER = "superrent";
    static final String PASSWORD = "superrent";
    
    static Connection conn = null;
    
    public static Statement getStmt() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        }

        return conn.createStatement();
    }
}
