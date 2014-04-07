/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.SQLException;

/**
 *
 * @author lenovo
 */
public interface GenericDao<T, K> {
    public T find(K pk) throws SQLException;
    public boolean update(T value);
    public boolean add(T value);
    public boolean delete (K pk);
}
