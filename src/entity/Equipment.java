/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 *
 * @author Jingchuan Chen
 */
public class Equipment {

    public enum STATUS {

        AVAILABLE,
        UNAVAILABLE
    };
    private int equipmentId;
    private String equipmentType;
    private STATUS status;
    private String manufactor;
    private Date manufactorDate;
    private String mode;
    private int branchId;

    /**
     * @return the equipmentId
     */
    public int getEquipmentId() {
        return equipmentId;
    }

    /**
     * @param equipmentId the equipmentId to set
     */
    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    /**
     * @return the equipmentType
     */
    public String getEquipmentType() {
        return equipmentType;
    }

    /**
     * @param equipmentType the equipmentType to set
     */
    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
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

    /**
     * @return the manufactor
     */
    public String getManufactor() {
        return manufactor;
    }

    /**
     * @param manufactor the manufactor to set
     */
    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    /**
     * @return the manufactorDate
     */
    public Date getManufactorDate() {
        return manufactorDate;
    }

    /**
     * @param manufactorDate the manufactorDate to set
     */
    public void setManufactorDate(Date manufactorDate) {
        this.manufactorDate = manufactorDate;
    }

    /**
     * @return the mode
     */
    public String getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @return the branchId
     */
    public int getBranchId() {
        return branchId;
    }

    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

}
