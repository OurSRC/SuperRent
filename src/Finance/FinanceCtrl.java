package Finance;

import SystemOperations.BranchCtrl;
import Account.CustomerCtrl;
import Operate.RentCtrl;
import Operate.Reservation;
import Operate.ReserveCtrl;
import Vehicle.VehicleCtrl;
import SystemOperations.Branch;
import Operate.BuyInsurance;
import Account.Customer;
import Operate.Rent;
import Operate.ReservationInfo;
import Operate.ReserveEquipment;
import Operate.Return;
import Vehicle.VehicleClass;
import java.util.ArrayList;
import java.util.Date;

/**
 *<p>This FinanceCtrl class provides data access and control function of user entity for UI</p>
 */
public class FinanceCtrl {

    private static final int BASE_DAILY_IRATE_DIVISION = 100;
    private static final int EXCHANG_POINT_LOW = 1000;
    private static final int EXCHANG_POINT_HIGH = 1500;
    private static final String BOUNDARY_HIGH_CAR = "LUXURY";

    /**
     * This method calculate the cost of rental with given {@code t1, t2, weekRate, dayRate, hourRate}
     * @param t1
     * @param t2
     * @param weekRate
     * @param dayRate
     * @param hourRate
     * @return rental cost
     */
    public int cost(Date t1, Date t2, int weekRate, int dayRate, int hourRate) {
        TimeGroup tg = countTimes(t1, t2);
        int rental = tg.cWeeks * weekRate + tg.cDays * dayRate + tg.cHours * hourRate;
        return rental;
    }

    /**
     * This method calculate the cost of rental with given {@code t1, t2, weekRate, dayRate, hourRate}
     * @param t1
     * @param t2
     * @param weekRate
     * @param dayRate
     * @param hourRate
     * @param baseRate
     * @return rental rate
     */
    public int cost(Date t1, Date t2, int weekRate, int dayRate, int hourRate, int baseRate) {
        TimeGroup tg = countTimes(t1, t2);
        if (weekRate < 0) {
            weekRate = -1 * weekRate * baseRate / BASE_DAILY_IRATE_DIVISION;
        }
        if (dayRate < 0) {
            dayRate = -1 * dayRate * baseRate / BASE_DAILY_IRATE_DIVISION;
        }
        if (hourRate < 0) {
            hourRate = -1 * hourRate * baseRate / BASE_DAILY_IRATE_DIVISION;
        }

        int rental = tg.cWeeks * weekRate + tg.cDays * dayRate + tg.cHours * hourRate;
        return rental;
    }

    /**
     * This method find the cost of rental with given {@code t1, t2, reserveInfo}
     * @param t1
     * @param t2
     * @param reserveInfo
     * @return rental cost
     */
    public int rentalCost(Date t1, Date t2, ReservationInfo reserveInfo) {
        if (reserveInfo != null) {
            return cost(t1, t2, reserveInfo.getvWeeklyRate(), reserveInfo.getvDailyRate(), reserveInfo.getvHourlyRate());
        } else {
            return 0;
        }
    }

    /**
     * This method find the cost of equipment with given {@code t1, t2, reserveEquipment}
     * @param t1
     * @param t2
     * @param reserveEquipment
     * @return equipment cost
     */
    public int equipmentCost(Date t1, Date t2, ReserveEquipment reserveEquipment) {
        if (reserveEquipment != null) {
            int week = reserveEquipment.getEDailyRate() * 7;
            int day = reserveEquipment.getEDailyRate();
            int hour = reserveEquipment.getEHourlyRate();
            int ans = cost(t1, t2, week, day, hour);
            return ans;
        } else {
            return 0;
        }
    }

    /**
     * This method find the cost of insurance with given {@code t1, t2, insurance, basePrice}
     * @param t1
     * @param t2
     * @param insurance
     * @param basePrice
     * @return cost of insurance
     */
    public int insuraceCost(Date t1, Date t2, BuyInsurance insurance, int basePrice) {
        if (insurance != null) {
            int week = insurance.getWeeklyRate();
            int day = insurance.getDailyRate();
            int hour = insurance.getHourlyRate();
            int ans = cost(t1, t2, week, day, hour, basePrice);
            return ans;
        } else {
            return 0;
        }
    }

    /**
     * This method calculates the estimated reservation cost with given {@code reserve}
     * @param reserve
     * @return cost of estimate reservation
     */
    public int estimateReservationCost(Reservation reserve) {
        TimeGroup tg = countTimes(reserve.getReturnTime(), reserve.getPickupTime());
        ReservationInfo reserveInfo = reserve.getReserveInfo();
        int rental = rentalCost(reserve.getReturnTime(), reserve.getPickupTime(), reserveInfo);
        int equip = 0;
        ArrayList<ReserveEquipment> reserveEquipment = reserve.getReserveEquipment();
        for (ReserveEquipment anEquip : reserveEquipment) {
            equip += equipmentCost(reserve.getReturnTime(), reserve.getPickupTime(), anEquip);
        }
        int insurance = 0;
        ArrayList<BuyInsurance> reserveInsurances = reserve.getReserveInsurance();
        for (BuyInsurance anInsu : reserveInsurances) {
            insurance += insuraceCost(reserve.getReturnTime(), reserve.getPickupTime(), anInsu, reserveInfo.getvDailyRate());
        }

        return rental + equip + insurance;
    }

    /**
     * This method calculates Return Cost find {@link PaymentItem} with given {@code returnInfo, usePoint, roadStar}
     * @param returnInfo
     * @param usePoint
     * @param roadStar
     * @return
     */
    public ArrayList<PaymentItem> calulateReturnCost(Return returnInfo, boolean usePoint, boolean  roadStar) {
        ArrayList<PaymentItem> list = null;
        if (returnInfo == null) {
            return null;
        }

        BranchCtrl branchCtrl = new BranchCtrl();

        Date returnTime = returnInfo.getReturnTime();
        Rent rent = RentCtrl.getRentByContractNumber(returnInfo.getContractNo());
        Date rentTime = rent.getTime();
        Reservation reserve = ReserveCtrl.getReserve(rent.getReservationInfold());
        TimeGroup tg = countTimes(rentTime, returnTime);
        Branch branch = branchCtrl.getBranchById(reserve.getBranchId());

        int cRental = rentalCost(rentTime, returnTime, reserve.getReserveInfo());
        int cEquip = 0;
        ArrayList<ReserveEquipment> reserveEquipment = reserve.getReserveEquipment();
        for (ReserveEquipment anEquip : reserveEquipment) {
            cEquip += equipmentCost(rentTime, returnTime, anEquip);
        }
        int cInsurance = 0;
        ArrayList<BuyInsurance> reserveInsurances = reserve.getReserveInsurance();
        for (BuyInsurance anInsu : reserveInsurances) {
            cInsurance += insuraceCost(reserve.getReturnTime(), reserve.getPickupTime(), anInsu, reserve.getReserveInfo().getvDailyRate());
        }
        if(roadStar)
            cInsurance /= 2;

        int cMile = returnInfo.getOdometer() * branch.getPricePerKM();
        int cFuel = returnInfo.getFuelLevel() * branch.getFuelPrice();
        int cDamage = returnInfo.getDamageCost();
        int cDiscount = 0;
        int cDiscountDays = 0;
        if (usePoint) {
            cDiscountDays = calculateMenbershipPointEnoughForDays(reserve);
            int rentDays = tg.cDays + tg.cWeeks*7;
            if(cDiscountDays>rentDays)
                cDiscountDays = rentDays;
            if (cDiscountDays > 0) {
                cDiscount = -1 * cDiscountDays * reserve.getReserveInfo().getvDailyRate();
            }
        }
        
        System.out.println("FinanceCtrl->Rental:" + cRental);
        System.out.println("FinanceCtrl->cEquip:" + cEquip);
        System.out.println("FinanceCtrl->cInsurance:" + cInsurance);
        System.out.println("FinanceCtrl->cMile:" + cMile);
        System.out.println("FinanceCtrl->cFuel:" + cFuel);
        System.out.println("FinanceCtrl->cDamage:" + cDamage);
        System.out.println("FinanceCtrl->cDiscountDays:" + cDiscountDays);
        System.out.println("FinanceCtrl->cDiscount:" + cDiscount);

        list = new ArrayList<>();
        PaymentItem pRental = new PaymentItem(0, PaymentItem.ITEMTYPE.VEHICLE, "Rental", cRental, 1);
        PaymentItem pEquip = new PaymentItem(0, PaymentItem.ITEMTYPE.EQUIPMENT, "Equipment", cEquip, 1);
        PaymentItem pInsurance = new PaymentItem(0, PaymentItem.ITEMTYPE.INSURANCE, "Insurance", cInsurance, 1);
        PaymentItem pMile = new PaymentItem(0, PaymentItem.ITEMTYPE.MILE, "Additional Miles", cMile, 1);
        PaymentItem pFuel = new PaymentItem(0, PaymentItem.ITEMTYPE.FUEL, "Fuel", cFuel, 1);
        PaymentItem pDamage = new PaymentItem(0, PaymentItem.ITEMTYPE.DAMAGE, "Damage", cDamage, 1);
        PaymentItem pDiscount = cDiscountDays==0 ? null : new PaymentItem(0, PaymentItem.ITEMTYPE.POINTEXCHANGE, "Discount", cDiscount / cDiscountDays, cDiscountDays);

        if (cRental != 0) {
            list.add(pRental);
        }
        if (cEquip != 0) {
            list.add(pEquip);
        }
        if (cInsurance != 0) {
            list.add(pInsurance);
        }
        if (cMile != 0) {
            list.add(pMile);
        }
        if (cFuel != 0) {
            list.add(pFuel);
        }
        if (cDamage != 0) {
            list.add(pDamage);
        }
        if (cDiscount != 0) {
            list.add(pDiscount);
        }
        return list;
    }

    /**
     * This method find a {@link PaymentItem} with given {@code years, branchId}
     * @param years
     * @param branchId id of the branch
     * @return Payment Item
     */
    public PaymentItem calulateMembershipCost(int years, int branchId) {
        //ArrayList<PaymentItem> list = new ArrayList<>();
        BranchCtrl branchCtrl = new BranchCtrl();
        Branch branch = branchCtrl.getBranchById(branchId);
        return new PaymentItem(0, PaymentItem.ITEMTYPE.MEMBERSHIP, "Membership", branch.getClubMemberFeeRate(), years);
        //return list;
    }

    private class TimeGroup {

        int cWeeks;
        int cDays;
        int cHours;
    }

    private TimeGroup countTimes(Date t1, Date t2) {
        long week = 7 * 24 * 60 * 60 * 1000;
        long day = 24 * 60 * 60 * 1000;
        long hour = 60 * 60 * 1000;

        TimeGroup time = new TimeGroup();
        long delta = t1.getTime() - t2.getTime();
        if (delta < 0) {
            delta *= -1;
        }
        time.cWeeks = (int) (delta / week);
        delta = delta % week;
        time.cDays = (int) (delta / day);
        delta = delta % day;
        time.cHours = (int) (delta / hour);
        delta = delta % hour;
        if (delta > 0) {
            time.cHours++;
        }
        return time;
    }

    /**
     * This method calculates membership point for one day with given {@code reserve}
     * @param reserve
     * @return point per day
     */
    static public int calculateMembershipPointForOneDay(Reservation reserve) {
        int perDayPoint;
        VehicleCtrl vCtrl = new VehicleCtrl();
        VehicleClass vc = vCtrl.findVehicleClass(reserve.getVehicleClass());
        VehicleClass lux = vCtrl.findVehicleClass(BOUNDARY_HIGH_CAR);
        if (vc.getVehicleType() == VehicleClass.TYPE.Car) {
            if (vc.getDailyRate() >= lux.getDailyRate()) {
                perDayPoint = EXCHANG_POINT_HIGH;
            } else {
                perDayPoint = EXCHANG_POINT_LOW;
            }
        } else {
            perDayPoint = EXCHANG_POINT_HIGH;
        }
        return perDayPoint;
    }

    /**
     * This method calculates membership point for days with given {@code reserve}
     * @param reserve
     * @return 
     */
    public int calculateMenbershipPointEnoughForDays(Reservation reserve) {
        TimeGroup tg = countTimes(reserve.getReturnTime(), reserve.getPickupTime());
        int resDays = tg.cDays + tg.cWeeks * 7;

        CustomerCtrl customerCtrl = new CustomerCtrl();
        Customer customer = customerCtrl.getCustomerById(reserve.getCustomerId());

        int perDayPoint = calculateMembershipPointForOneDay(reserve);

        int point = 0;
        if (customerCtrl.checkMembershipActive(customer)) {
            point = customer.getPoint();
        }

        int total = point / (perDayPoint);
        return resDays < total ? resDays : total;
    }
}
