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

    public enum TYPE {
        Car(1),
        Truck(2);
        
        private int value;
        
        private TYPE(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    };

    private String className;
    private TYPE vehicleType;
    private int hourlyRate;
    private int dailyRate;
    private int weeklyRate;

    public VehicleClass(String className, TYPE type, 
            int hourlyRate, int dailyRate, int weeklyRate) {
        
        this.className = className;
        this.vehicleType = type;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.weeklyRate = weeklyRate;
    }
    
    public VehicleClass() {}
    
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
    public TYPE getVehicleType() {
        return vehicleType;
    }

    /**
     * @param vehicleType the vehicleType to set
     */
    public void setVehicleType(TYPE vehicleType) {
        this.vehicleType = vehicleType;
    }
    
    public void setVehicleType(String vehicleType) {
        this.vehicleType = TYPE.valueOf(vehicleType);
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
        return weeklyRate;
    }

    /**
     * @param WeeklyRate the WeeklyRate to set
     */
    public void setWeeklyRate(int WeeklyRate) {
        this.weeklyRate = WeeklyRate;
    }

}
