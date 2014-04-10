/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Branch;
import entity.Equipment;
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
    private static Branch branch;

    @BeforeClass
    public static void setUp() {
    }
    @AfterClass
    public static void tearDownClass() {
    }
    @Before
    public void setUpClass() throws DaoException {

        branch = null;
        String branchName = "someName";
        Branch b = new Branch(branchName, "12313123", "some addr");
        boolean suc = bdao.add(b);
        if(suc){
            branch = bdao.findByName(branchName);
        }
    }
    
//    @After
//    public void tearDown() throws DaoException {
//
//        bdao.delete(branch);
//    }
//    
     @After
    public void tearDown() throws DaoException {
        EquipmentDao dao = new EquipmentDao();
        Equipment entity;
        entity = new Equipment("SomeType",Equipment.STATUS.AVAILABLE,
                "SomeManufacture",new Date(),"someMode",branch.getBranchID());
        dao.delete(entity);
        
    }

    @Test
    public void testCanCreate() {
        Equipment e = new Equipment();

        //eDao.add(create);
    }

    private Equipment createAEquipmentObject() throws DaoException {
        Equipment e = new Equipment("SomeType",Equipment.STATUS.AVAILABLE,
                "SomeManufacture",new Date(),"someMode",branch.getBranchID());
        
        EquipmentDao equipmentDAO = new EquipmentDao();

        boolean suc = equipmentDAO.add(e);
        assertTrue(suc);
        ArrayList<Equipment> alist = equipmentDAO.findEquipmentByType(e.getEquipmentType());
        assertTrue(alist!=null);
        boolean found = false;
        for (Equipment equipment : alist) {
            if(equipment.getManufactor().compareTo(e.getManufactor())==0){
                if(equipment.getMode().compareTo(e.getMode())==0)
                    found = true;
            }
        }
        assertTrue(found);
        return e;
    }

}
