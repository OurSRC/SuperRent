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
import Dao.EntityParser.DateParser;
import Dao.EntityParser.DatetimeParser;
import Dao.EntityParser.IntParser;
import Dao.EntityParser.StringParser;
import java.util.ArrayList;
import java.util.Date;

/**
 * <p>
 * This class provides basic access methods, for example, find for rent
 * entity.</p>
 */
public class RentDao extends AbstractDao<Rent> {

    protected static final String tb_name = "rent";
    protected static final AttributeParser ap[] = {
        new IntParser("ContractNo", "ContractNo"),
        new IntParser("ReservationInfoId", "ReservationInfold"),
        new IntParser("VehicleNo", "VehicleNo"),
        new IntParser("FuelLevel", "FuelLevel"),
        new IntParser("Odometer", "Odometer"),
        new StringParser("CreditCardNo", "CreditCardNo"),
        new IntParser("StaffId", "StaffId"),
        new DatetimeParser("Time", "Time")
    };

    protected static final int[] pkIndex = {0};
    protected static final boolean pkIsAutoGen = true;

    @Override
    protected Rent getInstance() {
        return new Rent();
    }

    /**
     * This method find a {@link Rent} with given {@code contractNo}
     *
     * @param contractNo contract number of rent
     * @return rent with contract number, null if non found
     * @throws DaoException
     */
    public Rent findByContractNo(String contractNo) throws DaoException {
        return findOne("ContractNo=" + SqlBuilder.wrapStr(contractNo));
    }

    /**
     * This method find a {@link Rent} with given {@code reservationInfoId}
     *
     * @param reservationInfoId reservation information id
     * @return rent with reservation information id, null if not found
     * @throws DaoException
     */
    public Rent findByReservationInfo(int reservationInfoId) throws DaoException {
        return findOne("ReservationInfoId=" + SqlBuilder.wrapInt(reservationInfoId));
    }

    /**
     * This method find a {@link Rent} with given
     * {@code startDate,endDate,branchId}
     *
     * @param startDate start date of the rent
     * @param endDate end date of the rent
     * @param branchId id of the branch
     * @return array list of matched records, null if non found
     * @throws DaoException
     */
    public ArrayList<Rent> findBetween(Date startDate, Date endDate, int branchId) throws DaoException {

        SqlBuilder qb = new SqlBuilder();

        if (branchId != 0) {
            SqlBuilder subQb = new SqlBuilder();
            String subQueue = subQb.select("ReservationInfoId")
                    .from("reservation_info")
                    .where("BranchId =" + SqlBuilder.wrapInt(branchId))
                    .isSubQueue().toString();
            subQueue = "(" + subQueue + ")";
            qb.cond("ReservationInfoId IN " + subQueue);
        }

        qb.cond("DATE(Time) <=" + SqlBuilder.wrapDate(endDate));
        qb.cond("DATE(Time) >= " + SqlBuilder.wrapDate(startDate));

        return find(qb.toString());
    }
}
