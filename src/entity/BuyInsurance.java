/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * Entity for buy_insurance table.
 * @author Jingchuan Chen
 */
public class BuyInsurance {

    private String insuranceName;
    private int reservationInfoId;
    private int hourlyRate;
    private int dailyRate;
    private int weeklyRate;

    public BuyInsurance() {
    }

    public BuyInsurance(String insuranceName, int reservationInfoId, int hourlyRate, int dailyRate, int weeklyRate) {
        this.insuranceName = insuranceName;
        this.reservationInfoId = reservationInfoId;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.weeklyRate = weeklyRate;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public int getReservationInfoId() {
        return reservationInfoId;
    }

    public void setReservationInfoId(int reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getWeeklyRate() {
        return weeklyRate;
    }

    public void setWeeklyRate(int weeklyRate) {
        this.weeklyRate = weeklyRate;
    }

}
