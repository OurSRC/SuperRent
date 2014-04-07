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
public class Reservation extends ReservationInfo {

    /**
     * @return the reservationNo
     */
    public int getReservationNo() {
        return reservationNo;
    }

    /**
     * @param reservationNo the reservationNo to set
     */
    public void setReservationNo(int reservationNo) {
        this.reservationNo = reservationNo;
    }

    /**
     * @return the resrevationInfoId
     */
    public int getResrevationInfoId() {
        return resrevationInfoId;
    }

    /**
     * @param resrevationInfoId the resrevationInfoId to set
     */
    public void setResrevationInfoId(int resrevationInfoId) {
        this.resrevationInfoId = resrevationInfoId;
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
        PENDING,
        RENTED,
        CANCELED
    }
    private int reservationNo;
    private int resrevationInfoId;
    private STATUS status;
    
    
    
}
