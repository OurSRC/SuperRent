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
public class EquipmentType {
    private String typeName;
    private int hourlyRate;
    private int DailyRate;

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
     * @return the DailyRate
     */
    public int getDailyRate() {
        return DailyRate;
    }

    /**
     * @param DailyRate the DailyRate to set
     */
    public void setDailyRate(int DailyRate) {
        this.DailyRate = DailyRate;
    }
}
