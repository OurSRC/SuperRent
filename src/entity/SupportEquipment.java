/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;

/**
 * Entity class for support_equipment table.
 * @author Jingchuan Chen
 */
public class SupportEquipment {

    private String equipmentType;
    private String vehicleClass;

    public SupportEquipment() {
    }

    public SupportEquipment(String EquipmentType, String VehicleClassName) {
        this.equipmentType = EquipmentType;
        this.vehicleClass = VehicleClassName;
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
     * @return the vehicleClass
     */
    public String getVehicleClass() {
        return vehicleClass;
    }

    /**
     * @param vehicleClass the vehicleClass to set
     */
    public void setVehicleClass(String vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

}
