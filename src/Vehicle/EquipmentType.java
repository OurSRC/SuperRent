/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vehicle;

import Finance.Price;

/**
 * Entity class for equipment_type table.
 */
public class EquipmentType {

    private String typeName;
    private int hourlyRate;
    private int dailyRate;

    public EquipmentType(String typeName, int hourlyRate, int DailyRate) {
        this.typeName = typeName;
        this.hourlyRate = hourlyRate;
        this.dailyRate = DailyRate;

    }

    public EquipmentType() {
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
     * @param DailyRate the dailyRate to set
     */
    public void setDailyRate(int DailyRate) {
        this.dailyRate = DailyRate;
    }

    public String getHourlyPrice() {
        return Price.toText(hourlyRate);
    }

    public boolean setHourlyPrice(String price) {
        hourlyRate = Price.toCent(price);
        return true;
    }

    public String getDailyPrice() {
        return Price.toText(dailyRate);
    }

    public boolean setDailyPrice(String price) {
        dailyRate = Price.toCent(price);
        return true;
    }

}
