/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Objects;

/**
 * Entity class for equipment table.
 * @author Jingchuan Chen
 */
public class Equipment {

    public enum STATUS {

        AVAILABLE,
        UNAVAILABLE;

        public int getValue() {
            return this == AVAILABLE ? 1 : 2;
        }
    };
    private int equipmentId;
    private String equipmentType;
    private STATUS status;
    private String manufactor;
    private Date manufactorDate;
    private String mode;
    private int branchId;

    public Equipment() {
    }

    public Equipment(String equipmentType, STATUS status, String manufactor, Date manufactorDate, String mode, int branchId) {
        this.equipmentType = equipmentType;
        this.status = status;
        this.manufactor = manufactor;
        this.manufactorDate = manufactorDate;
        this.mode = mode;
        this.branchId = branchId;
    }

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
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = STATUS.valueOf(status);
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.equipmentType);
        hash = 83 * hash + Objects.hashCode(this.status);
        hash = 83 * hash + Objects.hashCode(this.manufactor);
        hash = 83 * hash + Objects.hashCode(this.manufactorDate);
        hash = 83 * hash + Objects.hashCode(this.mode);
        hash = 83 * hash + this.branchId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Equipment other = (Equipment) obj;
        if (!Objects.equals(this.equipmentType, other.equipmentType)) {
            return false;
        }
        if (!Objects.equals(this.manufactor, other.manufactor)) {
            return false;
        }
        if (!Objects.equals(this.mode, other.mode)) {
            return false;
        }
        return true;
    }

}
