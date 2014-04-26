/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 * Entity class for insurance table.
 * @author Jingchuan Chen
 */
public class Insurance {
    
    private String name;
    private int hourlyRate;
    private int dailyRate;
    private int weeklyRate;

    public Insurance(String name, int hourlyRate, int dailyRate, int weeklyRate) {
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.weeklyRate = weeklyRate;
    }

    public Insurance() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
