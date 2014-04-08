/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

/**
 *
 * @author lenovo
 */
public interface GenericDao<T, K> {
    public T find(K pk) throws DaoException;
    public boolean update(T value) throws DaoException;
    public boolean add(T value) throws DaoException;
    public boolean delete (K pk) throws DaoException;
}
