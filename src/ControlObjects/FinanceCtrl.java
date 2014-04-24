package ControlObjects;

import entity.BuyInsurance;
import entity.ReservationInfo;
import entity.ReserveEquipment;
import entity.Return;
import finance.Payment;
import java.util.ArrayList;
import java.util.Date;

public class FinanceCtrl {

    public int estimateReservationCost(Reservation reserve) {
        TimeGroup tg = countTimes(reserve.getReturnTime(), reserve.getPickupTime());
        ReservationInfo reserveInfo = reserve.getReserveInfo();
        int rental = tg.cWeeks * reserveInfo.getvWeeklyRate() + tg.cDays * reserveInfo.getvDailyRate() + tg.cHours * reserveInfo.getvHourlyRate();
        int equip = 0;
        if( tg.cWeeks==0 ){
            ArrayList<ReserveEquipment> reserveEquipment = reserve.getReserveEquipment();
            for(ReserveEquipment anEquip: reserveEquipment){
                equip += tg.cDays * anEquip.getEDailyRate() + tg.cHours * anEquip.getEHourlyRate();
            }
        }
        int insurance = 0;
        ArrayList<BuyInsurance> reserveInsurances = reserve.getReserveInsurance();
        for(BuyInsurance anInsu: reserveInsurances){
            equip += tg.cWeeks * anInsu.getWeeklyRate() + tg.cDays * anInsu.getDailyRate() + tg.cHours * anInsu.getHourlyRate();
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
