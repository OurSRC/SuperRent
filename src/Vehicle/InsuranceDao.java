/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vehicle;

import Dao.EntityParser.StringParser;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.IntParser;
import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;
import java.util.ArrayList;

/**
 * <p>
 * This class provides basic access methods, for example, find
 * for equipmentType entity.</p>
 */
public class InsuranceDao extends AbstractDao<Insurance> {
    
    protected static final String tb_name = "insurance";

    protected static final AttributeParser ap[] = {
        new StringParser("Name", "Name"),
        new IntParser("HourlyRate", "HourlyRate"),
        new IntParser("DailyRate", "DailyRate"),
        new IntParser("WeeklyRate", "WeeklyRate"),
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = false;
    

    @Override
    protected Insurance getInstance() {
        return new Insurance();
    }
    
    /**
     * Find {@link Insurance} object by {@code InsuranceName}.
     * @param insuranceName The name of insurance to search with.
     * @return Matching {@link Insurance}.
     * @throws DaoException
     */
    public Insurance findByName(String insuranceName) throws DaoException {
        String cond = "Name = " + SqlBuilder.wrapStr(insuranceName);
        return findOne(cond);
    }

    /**
     * Return all {@link Insurance} in database.
     * @return ArrayList of all insurance in db.
     * @throws DaoException
     */
    public ArrayList<Insurance> findAll() throws DaoException {
        return all();
    }
}
