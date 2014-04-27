/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 * <p>
 * Exception throw by dao objects.</p>
 */
public class DaoException extends Exception{
    public DaoException(String message) {
        super(message);
    }
    
    public DaoException(String tb_name, String method_name) {
        super(tb_name + " table " + method_name + " error!");
    }
}
