/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbconn;

import java.sql.*;

/**
 * The DbConn class handles database connection. 
 * @author Jingchuan Chen
 */
public class DbConn {
    /*
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/super_rent";
    static final String USER = "superrent";
    static final String PASSWORD = "superrent";
    */
    
    static Connection conn = null;
    
    private static void setConn() throws SQLException {
        conn = DriverManager.getConnection(XmlParser.get("url"), XmlParser.get("user"), XmlParser.get("password"));
        //conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    
    /**
     * Return a Statement of the connection
     * @return Statement object.
     * @throws SQLException
     */
    public static Statement getStmt() throws SQLException {
        if (conn == null) {
            setConn();
        }

        return conn.createStatement();
    }
    
    /**
     * Return a prepared statement which can retrieve auto generated key.
     * @param sql Sql string to construct prepared statement
     * @return Generated PreparedStatement
     * @throws SQLException
     */
    public static PreparedStatement getPStmtWithAutoGenKey(String sql) throws SQLException {
        if (conn == null) {
            setConn();
        }
        
        return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    }
    
    /**
     * Return the connection object of database.
     * @return Connection object of database
     * @throws SQLException
     */
    public static Connection getConn() throws SQLException {
        if (conn == null) {
            setConn();
        }

        return conn;
    }
}
