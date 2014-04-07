/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Jingchuan Chen
 */
public class VehicleClass {

    public enum STATUS {
        CAR,
        TRUCK
    };

    private String className;
    private STATUS vehicleType;
    private int hourlyRate;
    private int dailyRate;
    private int WeeklyRate;

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @return the vehicleType
     */
    public STATUS getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(STATUS vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * @return the hourlyRate
     */
    public int getHourlyRate() {
        return hourlyRate;
    }

    /**
     * @param hourlyRate the hourlyRate to set
     */
    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * @return the dailyRate
     */
    public int getDailyRate() {
        return dailyRate;
    }

    /**
     * @param dailyRate the dailyRate to set
     */
    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    /**
     * @return the WeeklyRate
     */
    public int getWeeklyRate() {
        return WeeklyRate;
    }

    /**
     * @param WeeklyRate the WeeklyRate to set
     */
    public void setWeeklyRate(int WeeklyRate) {
        this.WeeklyRate = WeeklyRate;
    }

}
