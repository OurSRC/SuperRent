/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SystemOperations;

import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.EnumParser;
import Dao.EntityParser.IntParser;
import Dao.EntityParser.StringParser;

/**
 * <p>
 *  This class provides basic access methods, for example, find
 *  for branch entity.</p>
 */
public class BranchDao extends AbstractDao<Branch> {

    public static final String tb_name = "branch";

    public static final AttributeParser ap[] = {
        new IntParser("BranchId", "BranchID"),
        new StringParser("BranchName", "BranchName"),
        new StringParser("BranchPhone", "BranchPhone"),
        new StringParser("BranchAddress", "BranchAddress"),
        new IntParser("FuelPrice", "FuelPrice"),
        new IntParser("PricePerKM", "PricePerKM"),
        new IntParser("ClubMemberFeeRate", "ClubMemberFeeRate")
    };

    public static final int[] pkIndex = {0};

    public static final boolean pkIsAutoGen = true;

    @Override
    protected Branch getInstance() {
        return new Branch();
    }
    /**
     * This method find a {@link Branch} with given {@code id}
     * @param id branch id
     * @return branch with branch id, null if non found
     * @throws DaoException
     */
    public Branch findById(int id) throws DaoException {
        return findOne("BranchID=" + SqlBuilder.wrapInt(id));
    }

   /**
     * This method find a {@link Branch} with given {@code name}
     * @param name branch name
     * @return branch with branch name, null if non found
     * @throws DaoException
     */
    public Branch findByName(String name) throws DaoException {
        return findOne("BranchName=" + SqlBuilder.wrapStr(name));
    }

    /**
     * This method find a {@link Branch} with given {@code phone}
     * @param phone branch phone number
     * @return branch with branch number, null if non found
     * @throws DaoException
     */
    public Branch findByPhone(String phone) throws DaoException {
        return findOne("BranchPhone=" + SqlBuilder.wrapStr(phone));
    }

    /**
     * This method find a {@link Branch} with given {@code address}
     * @param address branch address
     * @return branch with branch address, null if non found
     * @throws DaoException
     */
    public Branch findByAddress(String address) throws DaoException {
        return findOne("BranchAddress=" + SqlBuilder.wrapStr(address));
    }
}
