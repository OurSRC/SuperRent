/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package report;

import SystemOperations.PdfGen;
import org.junit.Test;
import static org.junit.Assert.*;

import Vehicle.VehicleDao;

/**
 *
 * @author Jingchuan Chen
 */
public class PdfGenTest {
    
    public PdfGenTest() {
    }

    /**
     * Test of genDailyRentalReport method, of class PdfGen.
     */
    @Test
    public void testGenDailyRentalReport() throws Exception {
        VehicleDao dao = new VehicleDao();
        
        PdfGen.genDailyRentalReport(dao.all());
    }
    
}
