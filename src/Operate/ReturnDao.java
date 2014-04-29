/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operate;

import Dao.AbstractDao;
import Dao.DaoException;
import Dao.SqlBuilder;
import Dao.EntityParser.AttributeParser;
import Dao.EntityParser.DatetimeParser;
import Dao.EntityParser.IntParser;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * This class provides basic access methods, for example, find for rent
 * entity.</p>
 */
public class ReturnDao extends AbstractDao<Return> {

    public static final String tb_name = "return_record";

    public static final AttributeParser ap[] = {
        new IntParser("ContractNo", "ContractNo"),
        new DatetimeParser("ReturnTime", "ReturnTime"),
        new IntParser("FuelLevel", "FuelLevel"),
        new IntParser("Odometer", "Odometer"),
        new IntParser("StaffId", "StaffId"),
        new IntParser("PaymentId", "PaymentId"),
        new IntParser("DamageCost", "DamageCost")
    };
    public static final int[] pkIndex = {0};
    public static final boolean pkIsAutoGen = false;
    private int branchID;

    @Override
    protected Return getInstance() {
        return new Return();
    }

    /**
     * This method find a {@link Return} with given {@code contractNo}
     *
     * @param contractNo contract number of return
     * @return return with contract number, null if non found
     * @throws DaoException
     */
    public Return findByContractNo(int contractNo) throws DaoException {
        return findOne("ContractNo=" + SqlBuilder.wrapInt(contractNo));
    }

    /**
     * This method find a {@link Return} with given
     * {@code startDate,endDate,branchId}
     *
     * @param startDate start date of the rent
     * @param endDate end date of the rent
     * @param branchId id of the branch
     * @return array list of matched records, null if non found
     * @throws DaoException
     */
    public ArrayList<Return> findBetween(Date startDate, Date endDate, int branchId) throws DaoException {
        SqlBuilder qb = new SqlBuilder();
        
        if (branchId != 0) {
            SqlBuilder subQb = new SqlBuilder();
            String subQueue = subQb.select("rent.ContractNo")
                    .from("reservation_info res").from("rent")
                    .where("res.ReservationInfoId = rent.ReservationInfoId")
                    .where("res.BranchId =" + SqlBuilder.wrapInt(branchId))
                    .isSubQueue().toString();
            subQueue = "(" + subQueue + ")";
            qb.cond("ContractNo IN " + subQueue);
        }

        qb.cond("DATE(ReturnTime) <=" + SqlBuilder.wrapDate(endDate));
        qb.cond("DATE(ReturnTime) >= " + SqlBuilder.wrapDate(startDate));

        return find(qb.toString());
    }
}
