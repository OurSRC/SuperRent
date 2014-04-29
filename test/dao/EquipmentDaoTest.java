/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Dao.DaoException;
import Vehicle.EquipmentTypeDao;
import Vehicle.EquipmentDao;
import SystemOperations.BranchDao;
import SystemOperations.Branch;
import Vehicle.Equipment;
import Vehicle.EquipmentType;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

/**
 *
 * @author Xi Yang
 */
public class EquipmentDaoTest {

    private static EquipmentDao eDao = new EquipmentDao();
    private static BranchDao bdao = new BranchDao();
    private static EquipmentTypeDao eTypeDao = new EquipmentTypeDao();
    private static EquipmentType type = new EquipmentType("same2",15,16);
    private static Branch branch;

    @BeforeClass
    public static void setUpClass() throws DaoException {

        branch = null;
        String branchName = "someNassasdll";
        Branch b = new Branch(branchName, "12313123", "some addr", 18, 130, 1000);
        boolean suc = bdao.add(b);
        if (suc) {
            branch = bdao.findByName(branchName);
        }
        
        eTypeDao.add(type);
    }

    @AfterClass
    public static void tearDown() throws DaoException {
       bdao.delete(branch);
       eTypeDao.delete(type);
    }

    @Test
    public void testCanCreateAndFindAndDelete()throws DaoException{
        Equipment e = getAEquipmentObject();
        assertTrue(eDao.add(e));
        ArrayList<Equipment> alist = eDao.findEquipmentByType(e.getEquipmentType());
        assertTrue(alist.contains(e));
        clear(e);
        assertFalse(eDao.findEquipmentByType(e.getEquipmentType()).contains(e));
    }
    
    @Test
    public void testUpdate() throws DaoException{
        Equipment e = getAEquipmentObject();
        eDao.add(e);
        Equipment storedE = eDao.findEquipmentByType(e.getEquipmentType()).get(0);
        String expected = "AnotherManufacture";
        storedE.setManufactor(expected);
        eDao.update(storedE);
        Equipment updatedE = eDao.findEquipmentById(storedE.getEquipmentId());
        assertEquals(updatedE.getManufactor(), expected);
        eDao.delete(storedE);
    }

    private Equipment getAEquipmentObject() throws DaoException {
        Equipment e = new Equipment(type.getTypeName(), Equipment.STATUS.AVAILABLE,
                "SomeManufacture", new Date(), "someMode", branch.getBranchID());

        return e;
    }
    private void clear(Equipment e)throws DaoException{
        ArrayList<Equipment> alist = eDao.findEquipmentByType(e.getEquipmentType());
        for (Equipment equipment : alist) {
            eDao.delete(equipment);
        }
    }
    
}
