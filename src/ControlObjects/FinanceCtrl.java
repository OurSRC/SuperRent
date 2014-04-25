package ControlObjects;

import entity.BuyInsurance;
import entity.ReservationInfo;
import entity.ReserveEquipment;
import entity.Return;
import entity.VehicleClass;
import finance.Payment;
import java.util.ArrayList;
import java.util.Date;

public class FinanceCtrl {
    private static int BASE_DAILY_IRATE_DIVISION = 100;

    public int cost(Date t1, Date t2, int weekRate, int dayRate, int hourRate){
        TimeGroup tg = countTimes(t1, t2);
        int rental = tg.cWeeks * weekRate + tg.cDays * dayRate + tg.cHours * hourRate;
        return rental;
    }
    
    public int cost(Date t1, Date t2, int weekRate, int dayRate, int hourRate, int baseRate){
        TimeGroup tg = countTimes(t1, t2);
        if(weekRate<0)
            weekRate = -1*weekRate*baseRate/BASE_DAILY_IRATE_DIVISION;
        if(dayRate<0)
            dayRate = -1*dayRate*baseRate/BASE_DAILY_IRATE_DIVISION;
        if(hourRate<0)
            hourRate = -1*hourRate*baseRate/BASE_DAILY_IRATE_DIVISION;
        
        int rental = tg.cWeeks * weekRate + tg.cDays * dayRate + tg.cHours * hourRate;
        return rental;
    }
    
    public int rentalCost(Date t1, Date t2, ReservationInfo reserveInfo){
        if(reserveInfo!=null)
            return cost(t1, t2, reserveInfo.getvWeeklyRate(), reserveInfo.getvDailyRate(), reserveInfo.getvHourlyRate());
        else
            return 0;
    }
    
    public int equipmentCost(Date t1, Date t2, ReserveEquipment reserveEquipment){
        if(reserveEquipment!=null)
            return cost(t1, t2, 0, reserveEquipment.getEDailyRate(), reserveEquipment.getEHourlyRate());
        else
            return 0;
    }
    
    public int insuraceCost(Date t1, Date t2, BuyInsurance insurance, int basePrice){
        if(insurance!=null){
            int week = insurance.getWeeklyRate();
            int day = insurance.getDailyRate();
            int hour = insurance.getHourlyRate();            
            return cost(t1, t2, week, day, hour, basePrice);
        }else{
            return 0;
        }
    }

    public int estimateReservationCost(Reservation reserve) {
        TimeGroup tg = countTimes(reserve.getReturnTime(), reserve.getPickupTime());
        ReservationInfo reserveInfo = reserve.getReserveInfo();
        int rental = rentalCost(reserve.getReturnTime(), reserve.getPickupTime(), reserveInfo);
        int equip = 0;
        if( tg.cWeeks==0 ){
            ArrayList<ReserveEquipment> reserveEquipment = reserve.getReserveEquipment();
            for(ReserveEquipment anEquip: reserveEquipment){
                equip += equipmentCost(reserve.getReturnTime(), reserve.getPickupTime(), anEquip);
            }
        }
        int insurance = 0;
        ArrayList<BuyInsurance> reserveInsurances = reserve.getReserveInsurance();
        for(BuyInsurance anInsu: reserveInsurances){
            insurance += insuraceCost(reserve.getReturnTime(), reserve.getPickupTime(), anInsu, reserveInfo.getvDailyRate());
        }
        
        return rental + equip + insurance;
    }

    public int calulateRentCost(Return returnInfo) {
        return 0;
    }

    public int calulateMembershipCost(int years) {
        return 0;
    }

    public Payment createPayment() {
        return null;
    }
    
    private class TimeGroup{
        int cWeeks;
        int cDays;
        int cHours;
    }
    private TimeGroup countTimes(Date t1, Date t2){
        long week = 7*24*60*60*1000;
        long day = 24*60*60*1000;
        long hour = 60*60*1000;

        TimeGroup time = new TimeGroup();
        long delta = t1.getTime() - t2.getTime();
        if(delta<0)
            delta *=-1;
        time.cWeeks = (int) (delta / week);
        delta = delta % week;
        time.cDays = (int) (delta / day);
        delta = delta % day;
        time.cHours = (int)( delta / hour);
        delta = delta % hour;
        if( delta >0)
            time.cHours++;
        return time;
    }
}
