/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 * Entity class for reserve_equipment table.
 * @author Jingchuan Chen
 */
public class ReserveEquipment {

    private int reservationInfoId;
    private String equipmentType;
    private int eHourlyRate;
    private int eDailyRate;

    public ReserveEquipment() {
    }

    public ReserveEquipment(int reservationInfoId, String equipmentType, int eHourlyRate, int eDailyRate) {
        this.reservationInfoId = reservationInfoId;
        this.equipmentType = equipmentType;
        this.eHourlyRate = eHourlyRate;
        this.eDailyRate = eDailyRate;
    }

    /**
     * @return the reservationInfoId
     */
    public int getReservationInfoId() {
        return reservationInfoId;
    }

    /**
     * @param reservationInfoId the reservationInfoId to set
     */
    public void setReservationInfoId(int reservationInfoId) {
        this.reservationInfoId = reservationInfoId;
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
        System.out.println("equipmentType : " + equipmentType);
        this.equipmentType = equipmentType;
    }

    /**
     * @return the eHourlyRate
     */
    public int getEHourlyRate() {
        return eHourlyRate;
    }

    /**
     * @param eHourlyRate the eHourlyRate to set
     */
    public void setEHourlyRate(int eHourlyRate) {
        this.eHourlyRate = eHourlyRate;
    }

    /**
     * @return the eDailyRate
     */
    public int getEDailyRate() {
        return eDailyRate;
    }

    /**
     * @param eDailyRate the eDailyRate to set
     */
    public void setEDailyRate(int eDailyRate) {
        this.eDailyRate = eDailyRate;
    }
}
