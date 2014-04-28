/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 * Entity class for branch table.
 */
public class Branch {

    private int branchID;
    private String branchName;
    private String branchPhone;
    private String branchAddress;
    private int fuelPrice;
    private int pricePerKM;
    private int clubMemberFeeRate;

    public Branch() {
    }

    public Branch(String branchName, String branchPhone, String branchAddress,
            int fuelPrice, int pricePerKM, int clubMemberFeeRate) {
        this.branchID = 0;
        this.branchName = branchName;
        this.branchPhone = branchPhone;
        this.branchAddress = branchAddress;
        this.fuelPrice = fuelPrice;
        this.pricePerKM = pricePerKM;
        this.clubMemberFeeRate = clubMemberFeeRate;
    }

    /**
     * @return the branchID
     */
    public int getBranchID() {
        return branchID;
    }

    /**
     * @param branchID the branchID to set
     */
    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    /**
     * @return the branchName
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * @param branchName the branchName to set
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * @return the branchPhone
     */
    public String getBranchPhone() {
        return branchPhone;
    }

    /**
     * @param branchPhone the branchPhone to set
     */
    public void setBranchPhone(String branchPhone) {
        this.branchPhone = branchPhone;
    }

    /**
     * @return the branchAddress
     */
    public String getBranchAddress() {
        return branchAddress;
    }

    /**
     * @param branchAddress the branchAddress to set
     */
    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }

    /**
     * @return the fuelPrice
     */
    public int getFuelPrice() {
        return fuelPrice;
    }

    /**
     * @param fuelPrice the fuelPrice to set
     */
    public void setFuelPrice(int fuelPrice) {
        this.fuelPrice = fuelPrice;
    }

    /**
     * @return the pricePerKM
     */
    public int getPricePerKM() {
        return pricePerKM;
    }

    /**
     * @param pricePerKM the pricePerKM to set
     */
    public void setPricePerKM(int pricePerKM) {
        this.pricePerKM = pricePerKM;
    }

    /**
     * @return the clubMemberFeeRate
     */
    public int getClubMemberFeeRate() {
        return clubMemberFeeRate;
    }

    /**
     * @param clubMemberFeeRate the clubMemberFeeRate to set
     */
    public void setClubMemberFeeRate(int clubMemberFeeRate) {
        this.clubMemberFeeRate = clubMemberFeeRate;
    }
}
