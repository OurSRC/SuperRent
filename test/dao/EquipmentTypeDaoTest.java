/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.EquipmentType;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
/**
 *
 * @author Xi Yang
 */
public class EquipmentTypeDaoTest {
    EquipmentTypeDao etDao = new EquipmentTypeDao();
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
   @Before
    public void setUp() throws DaoException {
        EquipmentTypeDao dao = new EquipmentTypeDao();
        EquipmentType entity;
        entity = new EquipmentType("SomeName",15,16);
        dao.add(entity);
        
    }
    @After
    public void tearDown() throws DaoException {
        EquipmentTypeDao dao = new EquipmentTypeDao();
        EquipmentType entity;
        //entity = new EquipmentType("SomeName",15,16);
        entity = dao.findEquipmentTypeByTypename("SomeName");
        dao.delete(entity);
        
    }
    @Test
    public void testCanCreateAndDelete() throws Exception{
        EquipmentType type = getEquipmentTypeObject();
       
        assertTrue(etDao.add(type));
        assertTrue(etDao.delete(type));
        
    }
    
    @Test
    public void testCanUpdate() throws Exception {
       EquipmentType type = getEquipmentTypeObject();
       
       etDao.add(type);
       int rate = 10;
       type.setDailyRate(10);
       
       etDao.update(type);
       EquipmentType storedType = etDao.findEquipmentTypeByTypename(type.getTypeName());
       assertEquals(type.getDailyRate(), storedType.getDailyRate());
       etDao.delete(storedType);
    }
    private EquipmentType getEquipmentTypeObject(){
        EquipmentType type = new EquipmentType("SomName",15,16);//
        type.setDailyRate(15);
        type.setHourlyRate(16);
        type.setTypeName("SomName");
        return type;
    }
    
}
