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
public class Insurance {

    /**
     * @return the insuranceId
     */
    public int getInsuranceId() {
        return insuranceId;
    }

    /**
     * @param insuranceId the insuranceId to set
     */
    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the rate
     */
    public int getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(int rate) {
        this.rate = rate;
    }

    /**
     * @return the status
     */
    public STATUS getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(STATUS status) {
        this.status = status;
    }
    public enum STATUS {

        AVAILABLE,
        UNAVAILABLE
    };
    private int insuranceId;
    private String name;
    private int rate;
    private STATUS status;
    
    
    
}
